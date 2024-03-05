package com.example.HustLearning.service.Impl;


import com.example.HustLearning.dto.PageDTO;
import com.example.HustLearning.dto.request.SearchParamReq;
import com.example.HustLearning.dto.request.TopicReq;
import com.example.HustLearning.dto.response.TopicRes;
import com.example.HustLearning.entity.Topic;
import com.example.HustLearning.mapper.Impl.TopicMapperImpl;
import com.example.HustLearning.repository.TopicRepository;
import com.example.HustLearning.service.TopicService;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.PersistenceContextType;
import jakarta.persistence.TypedQuery;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
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
public class TopicServiceImpl implements TopicService {

    @PersistenceContext(type = PersistenceContextType.EXTENDED)
    private EntityManager entityManager;

    private  final TopicRepository topicRepository;
    private  final TopicMapperImpl topicMapper;

    @Override
    public List<TopicRes> getAllTopic() {
        List<Topic> topics = topicRepository.findAll();
        return topicMapper.toDTOList(topics);
    }

    @Override
    public Topic getTopicById(long id) {
     return topicRepository.findById(id).orElse(null);

    }

    @Override
    public void addTopic(TopicReq topicReq) {
        Topic topic = topicMapper.toEntity(topicReq);
        topicRepository.save(topic);
    }


    @Override
    public void deleteTopicById(long id) {
       topicRepository.deleteById(id);
    }

    @Override
    public PageDTO<TopicRes> search(SearchParamReq searchParamReq) {

        CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
        CriteriaQuery<Topic> criteriaQuery = criteriaBuilder.createQuery(Topic.class);
        Root<Topic> root = criteriaQuery.from(Topic.class);
        List<Predicate> predicates = new ArrayList<>();

        // Filter by text (if provided)
        String searchText = "%" + searchParamReq.text + "%";
        Predicate contentLike = criteriaBuilder.like(root.get("content"), searchText);
        predicates.add(contentLike);

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
        TypedQuery<Topic> query = entityManager.createQuery(criteriaQuery);
        int totalRows = query.getResultList().size();
        List<Topic> results = query
            .setFirstResult((searchParamReq.page - 1) * searchParamReq.size) // Offset
            .setMaxResults(searchParamReq.size) // Limit
            .getResultList();
        PageDTO<TopicRes> userResPageDTO = new PageDTO<>(topicMapper.toDTOList(results), searchParamReq.page, totalRows);

        return userResPageDTO;
    }
}
