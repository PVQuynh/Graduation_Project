package com.example.HustLearning.mapper.Impl;

import com.example.HustLearning.dto.request.TopicReq;
import com.example.HustLearning.dto.response.TopicRes;
import com.example.HustLearning.entity.Topic;
import com.example.HustLearning.mapper.TopicMapper;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class TopicMapperImpl implements TopicMapper {

    @Override
    public Topic toEntity(TopicReq dto) {
        ModelMapper modelMapper = new ModelMapper();

        return modelMapper.map(dto, Topic.class);
    }

    @Override
    public TopicRes toDTO(Topic entity) {
        ModelMapper modelMapper = new ModelMapper();

        return modelMapper.map(entity, TopicRes.class);
    }

    @Override
    public List<TopicRes> toDTOList(List<Topic> entityList) {
        return entityList.stream().map(entity->toDTO(entity)).collect(Collectors.toList());
    }

    @Override
    public List<Topic> toEntityList(List<TopicReq> dtoList) {
        return dtoList.stream().map(dto->toEntity(dto)).collect(Collectors.toList());
    }

}
