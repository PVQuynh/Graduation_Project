package com.example.HustLearning.service.Impl;

import com.example.HustLearning.constant.DataStatus;
import com.example.HustLearning.dto.PageDTO;
import com.example.HustLearning.dto.request.DataProvideReq;
import com.example.HustLearning.dto.request.DataRejectReq;
import com.example.HustLearning.dto.request.DataSearchparam;
import com.example.HustLearning.dto.response.DataCollectionRes;
import com.example.HustLearning.dto.response.SearchDataRes;
import com.example.HustLearning.entity.DataCollection;
import com.example.HustLearning.entity.Vocabulary;
import com.example.HustLearning.exception.BusinessLogicException;
import com.example.HustLearning.mapper.DataCollectionMapper;
import com.example.HustLearning.mapper.SearchDataMapper;
import com.example.HustLearning.repository.DataCollectionRepository;
import com.example.HustLearning.repository.VocabularyRepository;
import com.example.HustLearning.service.DataCollectionService;
import com.example.HustLearning.utils.EmailUtils;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.PersistenceContextType;
import jakarta.persistence.TypedQuery;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Join;
import jakarta.persistence.criteria.Predicate;
import jakarta.persistence.criteria.Root;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.util.ObjectUtils;

@Service
@RequiredArgsConstructor
public class DataCollectionServiceImpl implements DataCollectionService {

  @PersistenceContext(type = PersistenceContextType.EXTENDED)
  private EntityManager entityManager;

  private final DataCollectionRepository dataCollectionRepository;

  private final VocabularyRepository vocabRepository;

  private final DataCollectionMapper dataCollectionMapper;

  private final SearchDataMapper searchDataMapper;

  @Override
  public void sendData(DataProvideReq dataProvideReq) {
    String email = EmailUtils.getCurrentUser();
    Vocabulary vocabulary = vocabRepository.findVocabulariesById(dataProvideReq.getVocab_id())
        .orElseThrow(BusinessLogicException::new);
    if (!ObjectUtils.isEmpty(email)) {
      DataCollection dataCollection = DataCollection.builder()
          .dataLocation(dataProvideReq.getDataLocation())
          .volunteerEmail(email)
          .vocab(vocabulary)
          .status(DataStatus.WAITING)
          .build();
      dataCollectionRepository.save(dataCollection);
    }

  }

  @Override
  public void approve(long dataCollectionId) {
    String email = EmailUtils.getCurrentUser();

    if (!ObjectUtils.isEmpty(email)) {
      DataCollection dataCollection = dataCollectionRepository.findById(dataCollectionId)
          .orElseThrow(() -> new BusinessLogicException());

      if (dataCollection.getStatus() == DataStatus.APPROVED) {
        throw new BusinessLogicException();
      }

      dataCollection.setStatus(DataStatus.APPROVED);
      dataCollection.setAdminEmail(email);

      dataCollectionRepository.save(dataCollection);
    }
  }

  @Override
  public void reject(DataRejectReq dataRejectReq) {
    String email = EmailUtils.getCurrentUser();
    if (!ObjectUtils.isEmpty(email)) {
      DataCollection dataCollection = dataCollectionRepository.findById(
              dataRejectReq.dataCollectionId)
          .orElseThrow(() -> new BusinessLogicException());
      if (dataCollection.getStatus() == DataStatus.REJECTED) {
        throw new BusinessLogicException();
      }
      dataCollection.setStatus(DataStatus.REJECTED);
      dataCollection.setAdminEmail(email);
      dataCollection.setFeedBack(dataRejectReq.feedBack);
      dataCollectionRepository.save(dataCollection);
    }
  }

  @Override
  public List<DataCollection> getHistory() {
    String email = EmailUtils.getCurrentUser();
    if (!ObjectUtils.isEmpty(email)) {
      return dataCollectionRepository.findByEmail(email).orElse(null);

    }
    ;
    return null;
  }

  @Override
  public PageDTO<SearchDataRes> getApproved(DataSearchparam dataSearchparam) throws ParseException {
    String email = EmailUtils.getCurrentUser();



    if (!ObjectUtils.isEmpty(email)) {
      dataSearchparam.setVolunteerEmail(email);
    }
    return searchDataCollection(dataSearchparam);
  }

  @Override
  public List<DataCollectionRes> getPending() {

    List<DataCollection> dataCollectionList = dataCollectionRepository.getPending().orElse(null);

    return dataCollectionMapper.toDTOList(dataCollectionList);

  }


  @Override
  public PageDTO<SearchDataRes> searchDataCollection( DataSearchparam dataSearchparam)
      throws ParseException {
    CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
    CriteriaQuery<DataCollection> criteriaQuery = criteriaBuilder.createQuery(DataCollection.class);
    Root<DataCollection> root = criteriaQuery.from(DataCollection.class);
    List<Predicate> predicates = new ArrayList<>();

    if (!ObjectUtils.isEmpty(dataSearchparam.volunteerEmail)  ) {
      Predicate emailLike = criteriaBuilder.equal(root.get("volunteerEmail"), dataSearchparam.volunteerEmail);
      predicates.add(emailLike);
    }
    if (dataSearchparam.status!=300) {
      Predicate statusLike = criteriaBuilder.equal(root.get("status"), dataSearchparam.status);
      predicates.add(statusLike);
    }

    if (!ObjectUtils.isEmpty(dataSearchparam.vocab)) {
      Join<DataCollection, Vocabulary> vocabJoin = root.join("vocab");
      Predicate vocabLike = criteriaBuilder.like(criteriaBuilder.lower(vocabJoin.get("content")),
          "%" + dataSearchparam.vocab.toLowerCase() + "%");
      predicates.add(vocabLike);
    }

    if (!ObjectUtils.isEmpty(dataSearchparam.createdFrom)) {
      SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
      Date createdFrom = dateFormat.parse(dataSearchparam.createdFrom);
      Predicate createdFromLike = criteriaBuilder.greaterThanOrEqualTo(root.get("created"),
          createdFrom);
      predicates.add(createdFromLike);
    }

    if (!ObjectUtils.isEmpty(dataSearchparam.createdTo)) {
      SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
      Date createdTo = dateFormat.parse(dataSearchparam.createdTo);
      Predicate createdToLike = criteriaBuilder.lessThanOrEqualTo(root.get("created"), createdTo);
      predicates.add(createdToLike);
    }

    // Filter by descending and orderBy (if provided)
    if (!ObjectUtils.isEmpty(dataSearchparam.ascending) && !ObjectUtils.isEmpty(
        dataSearchparam.orderBy)) {
      if (dataSearchparam.ascending) {
        criteriaQuery.orderBy(criteriaBuilder.asc(root.get(dataSearchparam.orderBy)));
      } else {
        criteriaQuery.orderBy(criteriaBuilder.desc(root.get(dataSearchparam.orderBy)));
      }
    }

    if (!predicates.isEmpty()) {

      criteriaQuery.where(predicates.toArray(new Predicate[0]));
    }
    TypedQuery<DataCollection> query = entityManager.createQuery(criteriaQuery);
    int totalRows = query.getResultList().size();
    List<DataCollection> results = query
        .setFirstResult((dataSearchparam.page - 1) * dataSearchparam.size) // Offset
        .setMaxResults(dataSearchparam.size) // Limit
        .getResultList();
    PageDTO<SearchDataRes> userResPageDTO = new PageDTO<>(searchDataMapper.toDTOList(results),
        dataSearchparam.page, totalRows);

    return userResPageDTO;
  }
}
