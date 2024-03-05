package com.example.HustLearning.service;

import com.example.HustLearning.dto.PageDTO;
import com.example.HustLearning.dto.request.SearchParamReq;
import com.example.HustLearning.dto.request.TopicReq;
import com.example.HustLearning.dto.response.TopicRes;
import com.example.HustLearning.entity.Topic;

import java.util.List;

public interface TopicService {

    List<TopicRes> getAllTopic();

    Topic getTopicById(long id);

    void addTopic(TopicReq topicReq);



    void deleteTopicById(long id);

    PageDTO<TopicRes> search(SearchParamReq searchParamReq);
}
