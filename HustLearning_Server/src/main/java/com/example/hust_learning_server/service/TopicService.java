package com.example.hust_learning_server.service;

import com.example.hust_learning_server.dto.PageDTO;
import com.example.hust_learning_server.dto.request.SearchTopicParamReq;
import com.example.hust_learning_server.dto.request.TopicReq;
import com.example.hust_learning_server.dto.request.UpdateTopicReq;
import com.example.hust_learning_server.dto.response.TopicRes;

import java.util.List;

public interface TopicService {

    List<TopicRes> getAllTopics(long classRoomId, String isPrivate, String contentSearch);

    List<TopicRes> getAllCommonTopics(long classRoomId);

    List<TopicRes> getAllPrivateTopics(long classRoomId);

    void addTopic(TopicReq topicReq);

    void updateTopic(UpdateTopicReq updateTopicReq);

    void deleteTopicById(long id);

    PageDTO<TopicRes> search(SearchTopicParamReq searchTopicParamReq);

    PageDTO<TopicRes> searchV2(int page, int size, String text, boolean ascending, String orderBy);


}
