package com.example.hust_learning_server.service.Impl;

import com.example.hust_learning_server.constant.DataStatus;
import com.example.hust_learning_server.dto.PageDTO;
import com.example.hust_learning_server.dto.request.*;
import com.example.hust_learning_server.dto.response.DataCollectionRes;
import com.example.hust_learning_server.dto.response.SearchDataRes;
import com.example.hust_learning_server.entity.DataCollection;
import com.example.hust_learning_server.entity.Vocabulary;
import com.example.hust_learning_server.exception.BusinessLogicException;
import com.example.hust_learning_server.mapper.DataCollectionMapper;
import com.example.hust_learning_server.mapper.SearchDataMapper;
import com.example.hust_learning_server.repository.DataCollectionRepository;
import com.example.hust_learning_server.repository.VocabularyRepository;
import com.example.hust_learning_server.service.DataCollectionService;
import com.example.hust_learning_server.utils.EmailUtils;
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
import java.util.Set;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.util.ObjectUtils;

@Service
@RequiredArgsConstructor
public class DataCollectionServiceImpl implements DataCollectionService {

    @PersistenceContext(type = PersistenceContextType.TRANSACTION)
    private EntityManager entityManager;

    private final DataCollectionRepository dataCollectionRepository;

    private final VocabularyRepository vocabRepository;

    private final DataCollectionMapper dataCollectionMapper;

    private final SearchDataMapper searchDataMapper;

    @Override
    public void sendData(DataProvideReq dataProvideReq) {
        Vocabulary vocabulary = vocabRepository.findById(dataProvideReq.getVocabularyId())
                .orElseThrow(BusinessLogicException::new);

        DataCollection dataCollection = DataCollection.builder()
                .dataLocation(dataProvideReq.getDataLocation())
                .vocabulary(vocabulary)
                .status(DataStatus.WAITING)
                .build();

        dataCollectionRepository.save(dataCollection);
    }

    @Override
    public void updateData(UpdateDataReq updateDataReq) {
        String email = EmailUtils.getCurrentUser();
        if (ObjectUtils.isEmpty(email)) {
            throw new BusinessLogicException();
        }

        DataCollection dataCollection = dataCollectionRepository.findById(updateDataReq.getDataCollectionId()).orElseThrow(BusinessLogicException::new);

        if (updateDataReq.getDataLocation() != null) {
            dataCollection.setDataLocation(updateDataReq.getDataLocation());
        }
        if (updateDataReq.getVocabularyId() != 0) {
            Vocabulary vocabulary = vocabRepository.findById(updateDataReq.getVocabularyId()).orElseThrow(BusinessLogicException::new);
            dataCollection.setVocabulary(vocabulary);
        }

        dataCollectionRepository.save(dataCollection);
    }

    @Override
    public List<DataCollectionRes> getAllMe() {
        String email = EmailUtils.getCurrentUser();
        if (ObjectUtils.isEmpty(email)) {
            throw new BusinessLogicException();
        }

        List<DataCollection> dataCollections = dataCollectionRepository.getAllMe(email).orElseThrow(BusinessLogicException::new);
        if (dataCollections.isEmpty()) throw new BusinessLogicException();

        return dataCollectionMapper.toDTOList(dataCollections);

    }

    @Override
    public List<DataCollectionRes> getPendingMe() {
        String email = EmailUtils.getCurrentUser();
        if (ObjectUtils.isEmpty(email)) {
            throw new BusinessLogicException();
        }

        List<DataCollection> dataCollections = dataCollectionRepository.getPendingMe(email).orElseThrow(BusinessLogicException::new);
        if (dataCollections.isEmpty()) throw new BusinessLogicException();


        return dataCollectionMapper.toDTOList(dataCollections);
    }

    @Override
    public List<DataCollectionRes> getApprovedMe() {
        String email = EmailUtils.getCurrentUser();
        if (ObjectUtils.isEmpty(email)) {
            throw new BusinessLogicException();
        }

        List<DataCollection> dataCollections = dataCollectionRepository.getApprovedMe(email).orElseThrow(BusinessLogicException::new);
        if (dataCollections.isEmpty()) throw new BusinessLogicException();

        return dataCollectionMapper.toDTOList(dataCollections);
    }

    @Override
    public List<DataCollectionRes> getRejectMe() {
        String email = EmailUtils.getCurrentUser();
        if (ObjectUtils.isEmpty(email)) {
            throw new BusinessLogicException();
        }

        List<DataCollection> dataCollections = dataCollectionRepository.getRejectMe(email).orElseThrow(BusinessLogicException::new);
        if (dataCollections.isEmpty()) throw new BusinessLogicException();

        return dataCollectionMapper.toDTOList(dataCollections);
    }

    @Override
    public PageDTO<SearchDataRes> searchDataCollectionForUser(DataSearchForUserParam dataSearchForUserParam) throws ParseException {
        String email = EmailUtils.getCurrentUser();
        if (ObjectUtils.isEmpty(email)) {
            throw new BusinessLogicException();
        }

        CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
        CriteriaQuery<DataCollection> criteriaQuery = criteriaBuilder.createQuery(DataCollection.class);
        Root<DataCollection> root = criteriaQuery.from(DataCollection.class);
        List<Predicate> predicates = new ArrayList<>();

        if (!ObjectUtils.isEmpty(email)) {
            Predicate emailLike = criteriaBuilder.equal(root.get("author"), email);
            predicates.add(emailLike);
        }

        if (Set.of(100, 200, 300).contains(dataSearchForUserParam.status)) {
            Predicate statusLike = criteriaBuilder.equal(root.get("status"), dataSearchForUserParam.status);
            predicates.add(statusLike);
        }

        if (!ObjectUtils.isEmpty(dataSearchForUserParam.vocabulary)) {
            Join<DataCollection, Vocabulary> vocabJoin = root.join("vocabulary");
            Predicate vocabLike = criteriaBuilder.like(criteriaBuilder.lower(vocabJoin.get("content")),
                    "%" + dataSearchForUserParam.vocabulary.toLowerCase() + "%");
            predicates.add(vocabLike);
        } else return null;

        if (!ObjectUtils.isEmpty(dataSearchForUserParam.createdFrom)) {
            SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
            Date createdFrom = dateFormat.parse(dataSearchForUserParam.createdFrom);
            Predicate createdFromLike = criteriaBuilder.greaterThanOrEqualTo(root.get("created"),
                    createdFrom);
            predicates.add(createdFromLike);
        }

        if (!ObjectUtils.isEmpty(dataSearchForUserParam.createdTo)) {
            SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
            Date createdTo = dateFormat.parse(dataSearchForUserParam.createdTo);
            Predicate createdToLike = criteriaBuilder.lessThanOrEqualTo(root.get("created"), createdTo);
            predicates.add(createdToLike);
        }

        // Filter by descending and orderBy (if provided)
        if (!ObjectUtils.isEmpty(dataSearchForUserParam.ascending) && !ObjectUtils.isEmpty(
                dataSearchForUserParam.orderBy)) {
            if (dataSearchForUserParam.ascending) {
                criteriaQuery.orderBy(criteriaBuilder.asc(root.get(dataSearchForUserParam.orderBy)));
            } else {
                criteriaQuery.orderBy(criteriaBuilder.desc(root.get(dataSearchForUserParam.orderBy)));
            }
        }

        if (!predicates.isEmpty()) {
            criteriaQuery.where(predicates.toArray(new Predicate[0]));
        }

        TypedQuery<DataCollection> query = entityManager.createQuery(criteriaQuery);
        int totalRows = query.getResultList().size();
        List<DataCollection> results = query
                .setFirstResult((dataSearchForUserParam.page - 1) * dataSearchForUserParam.size) // Offset
                .setMaxResults(dataSearchForUserParam.size) // Limit
                .getResultList();

        PageDTO<SearchDataRes> userResPageDTO = new PageDTO<>(searchDataMapper.toDTOList(results),
                dataSearchForUserParam.page, totalRows);

        return userResPageDTO;
    }

    @Override
    public List<DataCollectionRes> getPendingAdmin() {
        String email = EmailUtils.getCurrentUser();
        if (ObjectUtils.isEmpty(email)) {
            throw new BusinessLogicException();
        }

        List<DataCollection> dataCollectionList = dataCollectionRepository.getPendingAdmin().orElseThrow(BusinessLogicException::new);
        if (dataCollectionList.isEmpty()) throw new BusinessLogicException();

        return dataCollectionMapper.toDTOList(dataCollectionList);
    }

    @Override
    public PageDTO<SearchDataRes> searchDataCollectionForAdmin(DataSearchForAdminParam dataSearchForAdminParam) throws ParseException {
        String email = EmailUtils.getCurrentUser();
        if (ObjectUtils.isEmpty(email)) {
            throw new BusinessLogicException();
        }

        CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
        CriteriaQuery<DataCollection> criteriaQuery = criteriaBuilder.createQuery(DataCollection.class);
        Root<DataCollection> root = criteriaQuery.from(DataCollection.class);
        List<Predicate> predicates = new ArrayList<>();

        if (!ObjectUtils.isEmpty(dataSearchForAdminParam.volunteerEmail)) {
            Predicate emailLike = criteriaBuilder.equal(root.get("author"), dataSearchForAdminParam.volunteerEmail);
            predicates.add(emailLike);
        }

        if (Set.of(100, 200, 300).contains(dataSearchForAdminParam.status)) {
            Predicate statusLike = criteriaBuilder.equal(root.get("status"), dataSearchForAdminParam.status);
            predicates.add(statusLike);
        }

        if (!ObjectUtils.isEmpty(dataSearchForAdminParam.vocabulary)) {
            Join<DataCollection, Vocabulary> vocabJoin = root.join("vocabulary");
            Predicate vocabLike = criteriaBuilder.like(criteriaBuilder.lower(vocabJoin.get("content")),
                    "%" + dataSearchForAdminParam.vocabulary.toLowerCase() + "%");
            predicates.add(vocabLike);
        } else return null;

        if (!ObjectUtils.isEmpty(dataSearchForAdminParam.createdFrom)) {
            SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
            Date createdFrom = dateFormat.parse(dataSearchForAdminParam.createdFrom);
            Predicate createdFromLike = criteriaBuilder.greaterThanOrEqualTo(root.get("created"),
                    createdFrom);
            predicates.add(createdFromLike);
        }

        if (!ObjectUtils.isEmpty(dataSearchForAdminParam.createdTo)) {
            SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
            Date createdTo = dateFormat.parse(dataSearchForAdminParam.createdTo);
            Predicate createdToLike = criteriaBuilder.lessThanOrEqualTo(root.get("created"), createdTo);
            predicates.add(createdToLike);
        }

        // Filter by descending and orderBy (if provided)
        if (!ObjectUtils.isEmpty(dataSearchForAdminParam.ascending) && !ObjectUtils.isEmpty(
                dataSearchForAdminParam.orderBy)) {
            if (dataSearchForAdminParam.ascending) {
                criteriaQuery.orderBy(criteriaBuilder.asc(root.get(dataSearchForAdminParam.orderBy)));
            } else {
                criteriaQuery.orderBy(criteriaBuilder.desc(root.get(dataSearchForAdminParam.orderBy)));
            }
        }

        if (!predicates.isEmpty()) {

            criteriaQuery.where(predicates.toArray(new Predicate[0]));
        }
        TypedQuery<DataCollection> query = entityManager.createQuery(criteriaQuery);
        int totalRows = query.getResultList().size();
        List<DataCollection> results = query
                .setFirstResult((dataSearchForAdminParam.page - 1) * dataSearchForAdminParam.size) // Offset
                .setMaxResults(dataSearchForAdminParam.size) // Limit
                .getResultList();
        PageDTO<SearchDataRes> userResPageDTO = new PageDTO<>(searchDataMapper.toDTOList(results),
                dataSearchForAdminParam.page, totalRows);

        return userResPageDTO;
    }

    @Override
    public void approve(long id) {
        String email = EmailUtils.getCurrentUser();
        if (ObjectUtils.isEmpty(email)) {
            throw new BusinessLogicException();
        }

        DataCollection dataCollection = dataCollectionRepository.findById(id).orElseThrow(() -> new BusinessLogicException());

        if (dataCollection.getStatus() == DataStatus.APPROVED) {
            throw new BusinessLogicException();
        }

        dataCollection.setStatus(DataStatus.APPROVED);
        dataCollection.setAdminEmail(email);

        dataCollectionRepository.save(dataCollection);
    }

    @Override
    public void reject(DataRejectReq dataRejectReq) {
        String email = EmailUtils.getCurrentUser();
        if (ObjectUtils.isEmpty(email)) {
            throw new BusinessLogicException();
        }

        DataCollection dataCollection = dataCollectionRepository.findById(
                dataRejectReq.dataCollectionId).orElseThrow(() -> new BusinessLogicException());
        if (dataCollection.getStatus() == DataStatus.REJECTED) {
            throw new BusinessLogicException();
        }
        dataCollection.setStatus(DataStatus.REJECTED);
        dataCollection.setAdminEmail(email);
        dataCollection.setFeedBack(dataRejectReq.feedBack);
        dataCollectionRepository.save(dataCollection);
    }

    @Override
    public void delete(long id) {
        String email = EmailUtils.getCurrentUser();
        if (ObjectUtils.isEmpty(email)) {
            throw new BusinessLogicException();
        }

        dataCollectionRepository.deleteById(id);
    }


}
