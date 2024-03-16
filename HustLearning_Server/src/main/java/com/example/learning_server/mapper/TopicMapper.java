package com.example.learning_server.mapper;

import com.example.learning_server.dto.request.TopicReq;
import com.example.learning_server.dto.response.TopicRes;
import com.example.learning_server.entity.Topic;

import java.util.List;

public interface TopicMapper {

    Topic toEntity(TopicReq dto);

    TopicRes toDTO(Topic entity);

    List<TopicRes> toDTOList(List<Topic> entityList);

    List<Topic> toEntityList(List<TopicReq> dtoList);
}

