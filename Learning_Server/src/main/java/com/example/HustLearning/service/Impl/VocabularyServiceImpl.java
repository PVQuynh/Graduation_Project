package com.example.HustLearning.service.Impl;

import com.example.HustLearning.dto.PageDTO;
import com.example.HustLearning.dto.request.SearchParamReq;
import com.example.HustLearning.dto.request.UpdateVocabReq;
import com.example.HustLearning.dto.request.VocabReq;
import com.example.HustLearning.dto.response.TopicRes;
import com.example.HustLearning.dto.response.VocabRes;
import com.example.HustLearning.entity.DataCollection;
import com.example.HustLearning.entity.Topic;
import com.example.HustLearning.entity.Vocabulary;
import com.example.HustLearning.exception.BusinessLogicException;
import com.example.HustLearning.mapper.Impl.VocabularyMapperImpl;
import com.example.HustLearning.mapper.VocabMapper;
import com.example.HustLearning.repository.TopicRepository;
import com.example.HustLearning.repository.VocabularyRepository;
import com.example.HustLearning.service.VocabularySerivce;
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
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import org.springframework.util.ObjectUtils;

@Service
@RequiredArgsConstructor
public class VocabularyServiceImpl implements VocabularySerivce {

    @PersistenceContext(type = PersistenceContextType.EXTENDED)
    private EntityManager entityManager;

    private final VocabularyRepository vocabularyRepository;

    private  final VocabularyMapperImpl vocabularyMapper;

    @Override
    public List<VocabRes> getVocabulariesByTopicId(long topicId) {
        List<Vocabulary> vocabularies = vocabularyRepository.findVocabulariesByTopicId(topicId).orElse(null);
        return vocabularyMapper.toDTOList(vocabularies);
    }

    @Override
    public PageDTO<VocabRes> search(SearchParamReq searchParamReq) {
        CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
        CriteriaQuery<Vocabulary> criteriaQuery = criteriaBuilder.createQuery(Vocabulary.class);
        Root<Vocabulary> root = criteriaQuery.from(Vocabulary.class);
        List<Predicate> predicates = new ArrayList<>();

        // Filter by text (if provided)
        if (!ObjectUtils.isEmpty(searchParamReq.text)) {
            String searchText = "%" + searchParamReq.text + "%";
            Predicate contentLike = criteriaBuilder.like(root.get("content"), searchText);
            predicates.add(contentLike);
        }

        if (searchParamReq.topicId !=0){
            Join<Vocabulary, Topic> topicJoin = root.join("topic");
            Predicate topicLike = criteriaBuilder.equal(topicJoin.get("id"), searchParamReq.topicId);
            predicates.add(topicLike);
        }

        // Filter by descending and orderBy (if provided)
        if (!ObjectUtils.isEmpty(searchParamReq.ascending) && !ObjectUtils.isEmpty(searchParamReq.orderBy)) {
            if (searchParamReq.ascending) {
                criteriaQuery.orderBy(criteriaBuilder.asc(root.get(searchParamReq.orderBy)));
            } else {
                criteriaQuery.orderBy(criteriaBuilder.desc(root.get(searchParamReq.orderBy)));
            }
        }

        if (!predicates.isEmpty()) {

            criteriaQuery.where(predicates.toArray(new Predicate[0]));
        }
        TypedQuery<Vocabulary> query = entityManager.createQuery(criteriaQuery);
        int totalRows = query.getResultList().size();
        List<Vocabulary> results = query
                .setFirstResult((searchParamReq.page - 1) * searchParamReq.size) // Offset
                .setMaxResults(searchParamReq.size) // Limit
                .getResultList();
        PageDTO<VocabRes> userResPageDTO = new PageDTO<>(vocabularyMapper.toDTOList(results), searchParamReq.page, totalRows);

        return userResPageDTO;
    }

    @Override
    public void addVocabulary(VocabReq vocabReq) {
        Vocabulary vocabulary = vocabularyMapper.toEntity(vocabReq);
        vocabularyRepository.save(vocabulary);
    }

    @Override
    public void updateVocabulary(UpdateVocabReq updateVocabReq) {
        Vocabulary vocabulary = vocabularyRepository.findVocabulariesById(updateVocabReq.getVocabularyId()).orElseThrow(BusinessLogicException::new);

        if (updateVocabReq.getContent() != null) {
            vocabulary.setContent(updateVocabReq.getContent());
        }
        if (updateVocabReq.getImageLocation() !=null) {
            vocabulary.setImageLocation(updateVocabReq.getImageLocation());
        }
        if (updateVocabReq.getVideoLocation() != null) {
            vocabulary.setVideoLocation(updateVocabReq.getVideoLocation());
        }

        vocabularyRepository.save(vocabulary);
    }

    @Override
    public void deleteById(long id) {
        vocabularyRepository.deleteById(id);
    }


}
