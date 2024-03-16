package com.example.hust_learning_server.service;

import com.example.hust_learning_server.dto.PageDTO;
import com.example.hust_learning_server.dto.request.SearchParamReq;
import com.example.hust_learning_server.dto.request.TopicReq;
import com.example.hust_learning_server.dto.request.UpdateTopicReq;
import com.example.hust_learning_server.dto.response.TopicRes;

import java.util.List;

public interface TopicService {

    List<TopicRes> getAllTopic();

    void addTopic(TopicReq topicReq);

    void updateTopic(UpdateTopicReq updateTopicReq);

    void deleteTopicById(long id);

    PageDTO<TopicRes> search(SearchParamReq searchParamReq);
}
