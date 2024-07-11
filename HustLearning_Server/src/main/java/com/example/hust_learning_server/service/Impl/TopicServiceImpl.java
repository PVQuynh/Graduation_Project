package com.example.hust_learning_server.service.Impl;


import com.example.hust_learning_server.dto.PageDTO;
import com.example.hust_learning_server.dto.request.SearchTopicParamReq;
import com.example.hust_learning_server.dto.request.TopicReq;
import com.example.hust_learning_server.dto.request.UpdateTopicReq;
import com.example.hust_learning_server.dto.response.TopicRes;
import com.example.hust_learning_server.entity.ClassRoom;
import com.example.hust_learning_server.entity.Question;
import com.example.hust_learning_server.entity.Topic;
import com.example.hust_learning_server.entity.Vocabulary;
import com.example.hust_learning_server.exception.ConflictException;
import com.example.hust_learning_server.exception.ResourceNotFoundException;
import com.example.hust_learning_server.exception.UnAuthorizedException;
import com.example.hust_learning_server.mapper.Impl.TopicMapperImpl;
import com.example.hust_learning_server.repository.ClassRoomRepository;
import com.example.hust_learning_server.repository.QuestionRepository;
import com.example.hust_learning_server.repository.TopicRepository;
import com.example.hust_learning_server.repository.VocabularyRepository;
import com.example.hust_learning_server.service.TopicService;
import com.example.hust_learning_server.utils.CommonUtils;
import com.example.hust_learning_server.utils.EmailUtils;
import jakarta.persistence.EntityManager;
import jakarta.persistence.TypedQuery;
import jakarta.persistence.criteria.*;

import java.util.ArrayList;

import lombok.RequiredArgsConstructor;
import org.apache.logging.log4j.util.Strings;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.ObjectUtils;

@Service
@RequiredArgsConstructor
public class TopicServiceImpl implements TopicService {

//    @PersistenceContext(type = PersistenceContextType.TRANSACTION)
//    private EntityManager entityManager;

    private final EntityManager entityManager;
    private final TopicRepository topicRepository;
    private final QuestionRepository questionRepository;
    private final VocabularyRepository vocabularyRepository;
    private final TopicMapperImpl topicMapper;
    private final ClassRoomRepository classRoomRepository;

    @Override
    public List<TopicRes> getAllTopics(long classRoomId, String isPrivate, String contentSearch) {
        String email = EmailUtils.getCurrentUser();
        String role = EmailUtils.getRoleOfCurrentUser();
        int checkPrivate = CommonUtils.convertPrivateWithRole(isPrivate, role);
        if (Strings.isBlank(contentSearch)) contentSearch = null;
        List<Topic> topics = topicRepository.findAllTopics(classRoomId, checkPrivate, email, contentSearch);
        if (topics.isEmpty()) {
            return null;
        }
        return topicMapper.toDTOList(topics);
    }

    @Override
    public List<TopicRes> getAllCommonTopics(long classRoomId) {
        List<Topic> topics = topicRepository.findAllCommonTopicByClassRoomId(classRoomId);
        if (topics.isEmpty()) return null;
        return topicMapper.toDTOList(topics);
    }

    @Override
    public List<TopicRes> getAllPrivateTopics(long classRoomId) {
        String email = EmailUtils.getCurrentUser();
        if (ObjectUtils.isEmpty(email)) {
            throw new UnAuthorizedException();
        }
        List<Topic> topics = topicRepository.findAllPrivateTopicByClassRoomId(classRoomId,email);
        if (topics.isEmpty()) return null;
        return topicMapper.toDTOList(topics);
    }

    @Override
    public void addTopic(TopicReq topicReq) {
        String email = EmailUtils.getCurrentUser();
        if (ObjectUtils.isEmpty(email)) {
            throw new UnAuthorizedException();
        }
        Optional<Topic> existingTopic = topicRepository.findByContent(topicReq.getContent());
        if (existingTopic.isPresent()) {
            throw new ConflictException();
        }
        Topic topic = topicMapper.toEntity(topicReq);
        topicRepository.save(topic);
    }

    @Transactional
    @Override
    public void updateTopic(UpdateTopicReq updateTopicReq) {
        String email = EmailUtils.getCurrentUser();
        if (ObjectUtils.isEmpty(email)) {
            throw new UnAuthorizedException();
        }
        Topic topic = topicRepository.findById(updateTopicReq.getTopicId()).orElseThrow(ResourceNotFoundException::new);
        if (updateTopicReq.getContent() != null) {
            topic.setContent(updateTopicReq.getContent());
        }
        if (updateTopicReq.getImageLocation() != null) {
            topic.setImageLocation(updateTopicReq.getImageLocation());
        }
        if (updateTopicReq.getVideoLocation() != null) {
            topic.setVideoLocation(updateTopicReq.getVideoLocation());
        }
        topic.setPrivate(updateTopicReq.isPrivate());

        Optional<ClassRoom> classRoomOptional = classRoomRepository.findById(updateTopicReq.getTopicId());
        if (classRoomOptional.isPresent()) {
            if (topic.getClassRoom().getId() != classRoomOptional.get().getId()) {
                topic.setClassRoom(classRoomOptional.get());
            }
        }

        topicRepository.save(topic);
    }

    @Override
    public PageDTO<TopicRes> search(SearchTopicParamReq searchTopicParamReq) {
        CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
        CriteriaQuery<Topic> criteriaQuery = criteriaBuilder.createQuery(Topic.class);
        Root<Topic> root = criteriaQuery.from(Topic.class);
        List<Predicate> predicates = new ArrayList<>();

        // Filter by text (if provided)
        if (!ObjectUtils.isEmpty(searchTopicParamReq.text)) {
            String searchText = "%" + searchTopicParamReq.text + "%";
            Predicate contentLike = criteriaBuilder.like(root.get("content"), searchText);
            predicates.add(contentLike);
        } else return null;

        // common
        Predicate isPrivate = criteriaBuilder.equal(root.get("isPrivate"), false);
        predicates.add(isPrivate);

        // Filter by descending and orderBy (if provided)
        if (!ObjectUtils.isEmpty(searchTopicParamReq.ascending) && !ObjectUtils.isEmpty(searchTopicParamReq.orderBy)) {
            if (searchTopicParamReq.ascending) {
                criteriaQuery.orderBy(criteriaBuilder.asc(root.get(searchTopicParamReq.orderBy)));
            } else {
                criteriaQuery.orderBy(criteriaBuilder.desc(root.get(searchTopicParamReq.orderBy)));
            }
        }

        if (!predicates.isEmpty()) {
            criteriaQuery.where(predicates.toArray(new Predicate[0]));
        }

        TypedQuery<Topic> query = entityManager.createQuery(criteriaQuery);
        int totalRows = query.getResultList().size();
        List<Topic> results = query
                .setFirstResult((searchTopicParamReq.page - 1) * searchTopicParamReq.size) // Offset
                .setMaxResults(searchTopicParamReq.size) // Limit
                .getResultList();

        PageDTO<TopicRes> topicResPageDTO = new PageDTO<>(topicMapper.toDTOList(results), searchTopicParamReq.page, totalRows);
        return topicResPageDTO;
    }

    @Override
    public PageDTO<TopicRes> searchV2(int page, int size, String text, boolean ascending, String orderBy) {
        CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
        CriteriaQuery<Topic> criteriaQuery = criteriaBuilder.createQuery(Topic.class);
        Root<Topic> root = criteriaQuery.from(Topic.class);
        List<Predicate> predicates = new ArrayList<>();

        // Filter by text (if provided)
        if (!ObjectUtils.isEmpty(text)) {
            String searchText = "%" + text + "%";
            Predicate contentLike = criteriaBuilder.like(root.get("content"), searchText);
            predicates.add(contentLike);
        } else return null;

        // common
        Predicate isPrivate = criteriaBuilder.equal(root.get("isPrivate"), false);
        predicates.add(isPrivate);

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

        TypedQuery<Topic> query = entityManager.createQuery(criteriaQuery);
        int totalRows = query.getResultList().size();
        List<Topic> results = query
                .setFirstResult((page - 1) * size) // Offset
                .setMaxResults(size) // Limit
                .getResultList();

        PageDTO<TopicRes> topicResPageDTO = new PageDTO<>(topicMapper.toDTOList(results), page, totalRows);
        return topicResPageDTO;
    }

    @Override
    public void deleteTopicById(long id) {
        String email = EmailUtils.getCurrentUser();
        if (ObjectUtils.isEmpty(email)) {
            throw new UnAuthorizedException();
        }
        List<Vocabulary> vocabularyList = vocabularyRepository.findAllByTopicId(id);
        if (!vocabularyList.isEmpty()) {
            for (Vocabulary vocabulary : vocabularyList) vocabulary.setTopic(null);
            vocabularyRepository.deleteAll(vocabularyList);
        }
        topicRepository.deleteById(id);
    }
}
