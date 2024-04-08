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

    private final EntityManager entityManager;

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
    public List<DataCollectionRes> getOptionsListMe(int status) {
        String email = EmailUtils.getCurrentUser();
        if (ObjectUtils.isEmpty(email)) {
            throw new BusinessLogicException();
        }

        List<DataCollection> dataCollections = dataCollectionRepository.getOptionsList(email, status).orElseThrow(BusinessLogicException::new);
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

        if (dataSearchForUserParam.score >=0 && dataSearchForUserParam.score <= 10) {
            Predicate statusLike = criteriaBuilder.equal(root.get("score"), dataSearchForUserParam.score);
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
    public PageDTO<SearchDataRes> searchDataCollectionForUser_v2(int page, int size, String topic, String vocabulary, boolean ascending, String orderBy, String createdFromParam, String createdToParam, int status, float score) throws ParseException {
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

        if (Set.of(100, 200, 300).contains(status)) {
            Predicate statusLike = criteriaBuilder.equal(root.get("status"), status);
            predicates.add(statusLike);
        }

        if (score >=0 && score <= 10) {
            Predicate statusLike = criteriaBuilder.equal(root.get("score"), score);
            predicates.add(statusLike);
        }

        if (!ObjectUtils.isEmpty(vocabulary)) {
            Join<DataCollection, Vocabulary> vocabJoin = root.join("vocabulary");
            Predicate vocabLike = criteriaBuilder.like(criteriaBuilder.lower(vocabJoin.get("content")),
                    "%" + vocabulary.toLowerCase() + "%");
            predicates.add(vocabLike);
        } else return null;

        if (!ObjectUtils.isEmpty(createdFromParam)) {
            SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
            Date createdFrom = dateFormat.parse(createdFromParam);
            Predicate createdFromLike = criteriaBuilder.greaterThanOrEqualTo(root.get("created"),
                    createdFrom);
            predicates.add(createdFromLike);
        }

        if (!ObjectUtils.isEmpty(createdToParam)) {
            SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
            Date createdTo = dateFormat.parse(createdToParam);
            Predicate createdToLike = criteriaBuilder.lessThanOrEqualTo(root.get("created"), createdTo);
            predicates.add(createdToLike);
        }

        // Filter by descending and orderBy (if provided)
        if (!ObjectUtils.isEmpty(ascending) && !ObjectUtils.isEmpty(orderBy)) {
            if (ascending) {
                criteriaQuery.orderBy(criteriaBuilder.asc(root.get(orderBy)));
            } else {
                criteriaQuery.orderBy(criteriaBuilder.desc(root.get(orderBy)));
            }
        }

        if (!predicates.isEmpty()) {
            criteriaQuery.where(predicates.toArray(new Predicate[0]));
        }

        TypedQuery<DataCollection> query = entityManager.createQuery(criteriaQuery);
        int totalRows = query.getResultList().size();
        List<DataCollection> results = query
                .setFirstResult((page - 1) * size) // Offset
                .setMaxResults(size) // Limit
                .getResultList();

        PageDTO<SearchDataRes> userResPageDTO = new PageDTO<>(searchDataMapper.toDTOList(results), page, totalRows);

        return userResPageDTO;
    }

    //
    // Admin
    //

    @Override
    public List<DataCollectionRes> getOptionsListAdmin(int status) {
        String email = EmailUtils.getCurrentUser();
        if (ObjectUtils.isEmpty(email)) {
            throw new BusinessLogicException();
        }

        List<DataCollection> dataCollectionList = dataCollectionRepository.getOptionsListAdmin(status).orElseThrow(BusinessLogicException::new);
        if (dataCollectionList.isEmpty()) throw new BusinessLogicException();

        return dataCollectionMapper.toDTOList(dataCollectionList);
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

        if (dataSearchForAdminParam.score >=0 && dataSearchForAdminParam.score <= 10) {
            Predicate statusLike = criteriaBuilder.equal(root.get("score"), dataSearchForAdminParam.score);
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
    public PageDTO<SearchDataRes> searchDataCollectionForAdmin_v2(int page, int size, String volunteerEmail, String topic, String vocabulary, boolean ascending, String orderBy, String createdFromParam, String createdToParam, int status, float score) throws ParseException {
        String email = EmailUtils.getCurrentUser();
        if (ObjectUtils.isEmpty(email)) {
            throw new BusinessLogicException();
        }

        CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
        CriteriaQuery<DataCollection> criteriaQuery = criteriaBuilder.createQuery(DataCollection.class);
        Root<DataCollection> root = criteriaQuery.from(DataCollection.class);
        List<Predicate> predicates = new ArrayList<>();

        if (!ObjectUtils.isEmpty(volunteerEmail)) {
            Predicate emailLike = criteriaBuilder.equal(root.get("author"), volunteerEmail);
            predicates.add(emailLike);
        }

        if (Set.of(100, 200, 300).contains(status)) {
            Predicate statusLike = criteriaBuilder.equal(root.get("status"), status);
            predicates.add(statusLike);
        }

        if (score >=0 && score <= 10) {
            Predicate statusLike = criteriaBuilder.equal(root.get("score"), score);
            predicates.add(statusLike);
        }

        if (!ObjectUtils.isEmpty(vocabulary)) {
            Join<DataCollection, Vocabulary> vocabJoin = root.join("vocabulary");
            Predicate vocabLike = criteriaBuilder.like(criteriaBuilder.lower(vocabJoin.get("content")),
                    "%" +vocabulary.toLowerCase() + "%");
            predicates.add(vocabLike);
        } else return null;

        if (!ObjectUtils.isEmpty(createdFromParam)) {
            SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
            Date createdFrom = dateFormat.parse(createdFromParam);
            Predicate createdFromLike = criteriaBuilder.greaterThanOrEqualTo(root.get("created"),
                    createdFrom);
            predicates.add(createdFromLike);
        }

        if (!ObjectUtils.isEmpty(createdToParam)) {
            SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
            Date createdTo = dateFormat.parse(createdToParam);
            Predicate createdToLike = criteriaBuilder.lessThanOrEqualTo(root.get("created"), createdTo);
            predicates.add(createdToLike);
        }

        // Filter by descending and orderBy (if provided)
        if (!ObjectUtils.isEmpty(ascending) && !ObjectUtils.isEmpty(orderBy)) {
            if (ascending) {
                criteriaQuery.orderBy(criteriaBuilder.asc(root.get(orderBy)));
            } else {
                criteriaQuery.orderBy(criteriaBuilder.desc(root.get(orderBy)));
            }
        }

        if (!predicates.isEmpty()) {

            criteriaQuery.where(predicates.toArray(new Predicate[0]));
        }
        TypedQuery<DataCollection> query = entityManager.createQuery(criteriaQuery);
        int totalRows = query.getResultList().size();
        List<DataCollection> results = query
                .setFirstResult((page - 1) * size) // Offset
                .setMaxResults(size) // Limit
                .getResultList();
        PageDTO<SearchDataRes> userResPageDTO = new PageDTO<>(searchDataMapper.toDTOList(results), page, totalRows);

        return userResPageDTO;
    }

    @Override
    public void approve(DataReq dataReq) {
        String email = EmailUtils.getCurrentUser();
        if (ObjectUtils.isEmpty(email)) {
            throw new BusinessLogicException();
        }

        DataCollection dataCollection = dataCollectionRepository.findById(
                dataReq.dataCollectionId).orElseThrow(() -> new BusinessLogicException());

        if (dataCollection.getStatus() == DataStatus.APPROVED) {
            throw new BusinessLogicException();
        }

        dataCollection.setAdminEmail(email);
        dataCollection.setStatus(DataStatus.APPROVED);
        dataCollection.setScore(dataReq.score);
        dataCollection.setFeedBack(dataReq.feedBack);
        dataCollectionRepository.save(dataCollection);
    }

    @Override
    public void reject(DataReq dataReq) {
        String email = EmailUtils.getCurrentUser();
        if (ObjectUtils.isEmpty(email)) {
            throw new BusinessLogicException();
        }

        DataCollection dataCollection = dataCollectionRepository.findById(
                dataReq.dataCollectionId).orElseThrow(() -> new BusinessLogicException());

        if (dataCollection.getStatus() == DataStatus.REJECTED) {
            throw new BusinessLogicException();
        }

        dataCollection.setAdminEmail(email);
        dataCollection.setStatus(DataStatus.REJECTED);
        dataCollection.setScore(dataReq.score);
        dataCollection.setFeedBack(dataReq.feedBack);
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
