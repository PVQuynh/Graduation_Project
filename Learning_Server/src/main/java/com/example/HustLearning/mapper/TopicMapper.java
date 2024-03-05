package com.example.HustLearning.mapper;

import com.example.HustLearning.dto.request.TopicReq;
import com.example.HustLearning.dto.response.TopicRes;
import com.example.HustLearning.entity.Topic;

import java.util.List;

public interface TopicMapper {
    Topic toEntity(TopicReq dto);

    TopicRes toDTO(Topic entity);

    List<TopicRes> toDTOList(List<Topic> entityList);

    List<Topic> toEntityList(List<TopicReq> dtoList);
}

