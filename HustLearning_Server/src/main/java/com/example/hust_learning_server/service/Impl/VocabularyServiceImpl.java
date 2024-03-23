package com.example.hust_learning_server.service.Impl;

import com.example.hust_learning_server.dto.PageDTO;
import com.example.hust_learning_server.dto.request.*;
import com.example.hust_learning_server.dto.response.VocabularyRes;
import com.example.hust_learning_server.entity.Topic;
import com.example.hust_learning_server.entity.Vocabulary;
import com.example.hust_learning_server.exception.AlreadyExistsException;
import com.example.hust_learning_server.exception.BusinessLogicException;
import com.example.hust_learning_server.mapper.Impl.VocabularyMapperImpl;
import com.example.hust_learning_server.repository.VocabularyRepository;
import com.example.hust_learning_server.service.VocabularySerivce;
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

import java.util.ArrayList;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

import org.springframework.util.ObjectUtils;

@Service
@RequiredArgsConstructor
public class VocabularyServiceImpl implements VocabularySerivce {

    @PersistenceContext(type = PersistenceContextType.TRANSACTION)
//    @PersistenceContext(type = PersistenceContextType.EXTENDED)
    private EntityManager entityManager;

    private final VocabularyRepository vocabularyRepository;

    private final VocabularyMapperImpl vocabularyMapper;

    @Override
    public List<VocabularyRes> getVocabulariesByTopicId(long topicId) {
        List<Vocabulary> vocabularies = vocabularyRepository.findVocabulariesByTopicId(topicId).orElseThrow(() -> new BusinessLogicException());
        if (vocabularies.isEmpty()) throw new BusinessLogicException();

        return vocabularyMapper.toDTOList(vocabularies);
    }

    public Vocabulary getVocabulary(long id) {
        return vocabularyRepository.findById(id).orElseThrow(BusinessLogicException::new);
    }

    @Override
    public List<VocabularyRes> vocabularyLimits(VocabularyLimitReq vocabularyLimitReq) {
        Pageable pageable = PageRequest.of(vocabularyLimitReq.getPage() - 1, vocabularyLimitReq.getSize());

        List<Vocabulary> vocabularies = vocabularyRepository.findVocabulariesLimitByTopicId(vocabularyLimitReq.getTopicId(), pageable).orElseThrow(BusinessLogicException::new);
        if (vocabularies.isEmpty()) throw new BusinessLogicException();

        return vocabularyMapper.toDTOList(vocabularies);
    }

    @Override
    public PageDTO<VocabularyRes> search(SearchVocabularyParamReq searchVocabularyParamReq) {
        CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
        CriteriaQuery<Vocabulary> criteriaQuery = criteriaBuilder.createQuery(Vocabulary.class);
        Root<Vocabulary> root = criteriaQuery.from(Vocabulary.class);
        List<Predicate> predicates = new ArrayList<>();

        // Filter by text (if provided)
        if (!ObjectUtils.isEmpty(searchVocabularyParamReq.text)) {
            String searchText = "%" + searchVocabularyParamReq.text + "%";
            Predicate contentLike = criteriaBuilder.like(root.get("content"), searchText);
            predicates.add(contentLike);
        } else return null;

        if (searchVocabularyParamReq.topicId != 0) {
            Join<Vocabulary, Topic> topicJoin = root.join("topic");
            Predicate topicLike = criteriaBuilder.equal(topicJoin.get("id"), searchVocabularyParamReq.topicId);
            predicates.add(topicLike);
        }

        // Filter by descending and orderBy (if provided)
        if (!ObjectUtils.isEmpty(searchVocabularyParamReq.ascending) && !ObjectUtils.isEmpty(searchVocabularyParamReq.orderBy)) {
            if (searchVocabularyParamReq.ascending) {
                criteriaQuery.orderBy(criteriaBuilder.asc(root.get(searchVocabularyParamReq.orderBy)));
            } else {
                criteriaQuery.orderBy(criteriaBuilder.desc(root.get(searchVocabularyParamReq.orderBy)));
            }
        }

        if (!predicates.isEmpty()) {
            criteriaQuery.where(predicates.toArray(new Predicate[0]));
        }

        TypedQuery<Vocabulary> query = entityManager.createQuery(criteriaQuery);
        int totalRows = query.getResultList().size();
        List<Vocabulary> results = query
                .setFirstResult((searchVocabularyParamReq.page - 1) * searchVocabularyParamReq.size) // Offset
                .setMaxResults(searchVocabularyParamReq.size) // Limit
                .getResultList();

        PageDTO<VocabularyRes> vocabularyResPageDTO = new PageDTO<>(vocabularyMapper.toDTOList(results), searchVocabularyParamReq.page, totalRows);
        return vocabularyResPageDTO;
    }

    @Override
    public void addVocabulary(VocabularyReq vocabularyReq) {
        String email = EmailUtils.getCurrentUser();
        if (ObjectUtils.isEmpty(email)) {
            throw new BusinessLogicException();
        }

        Vocabulary vocabulary = vocabularyMapper.toEntity(vocabularyReq);
        vocabularyRepository.save(vocabulary);
    }

    @Override
    public void updateVocabulary(UpdateVocabularyReq updateVocabularyReq) {
        String email = EmailUtils.getCurrentUser();
        if (ObjectUtils.isEmpty(email)) {
            throw new BusinessLogicException();
        }

        Vocabulary vocabulary = vocabularyRepository.findById(updateVocabularyReq.getVocabularyId()).orElseThrow(BusinessLogicException::new);

        if (updateVocabularyReq.getContent() != null) {
            vocabulary.setContent(updateVocabularyReq.getContent());
        }
        if (updateVocabularyReq.getImageLocation() != null) {
            vocabulary.setImageLocation(updateVocabularyReq.getImageLocation());
        }
        if (updateVocabularyReq.getVideoLocation() != null) {
            vocabulary.setVideoLocation(updateVocabularyReq.getVideoLocation());
        }

        vocabularyRepository.save(vocabulary);
    }

    @Override
    public void deleteById(long id) {

    }


}
