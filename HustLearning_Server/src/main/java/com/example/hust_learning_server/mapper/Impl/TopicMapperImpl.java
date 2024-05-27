package com.example.hust_learning_server.mapper.Impl;

import com.example.hust_learning_server.dto.request.TopicReq;
import com.example.hust_learning_server.dto.response.TopicRes;
import com.example.hust_learning_server.entity.ClassRoom;
import com.example.hust_learning_server.entity.Topic;
import com.example.hust_learning_server.mapper.TopicMapper;
import com.example.hust_learning_server.repository.ClassRoomRepository;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
@RequiredArgsConstructor
public class TopicMapperImpl implements TopicMapper {
    private final ClassRoomRepository classRoomRepository;

    @Override
    public Topic toEntity(TopicReq dto) {
        ModelMapper modelMapper = new ModelMapper();
        Topic topic = modelMapper.map(dto, Topic.class);

        ClassRoom classRoom = classRoomRepository.findById(dto.getClassRoomId()).orElse(null);
        topic.setClassRoom(classRoom);
        return topic;
    }

    @Override
    public TopicRes toDTO(Topic entity) {
        ModelMapper modelMapper = new ModelMapper();
        TopicRes topicRes = modelMapper.map(entity, TopicRes.class);
        topicRes.setClassRoomId(entity.getClassRoom().getId());
        return topicRes;
    }

    @Override
    public List<TopicRes> toDTOList(List<Topic> entityList) {
        return entityList.stream().map(this::toDTO).collect(Collectors.toList());
    }

    @Override
    public List<Topic> toEntityList(List<TopicReq> dtoList) {
        return dtoList.stream().map(this::toEntity).collect(Collectors.toList());
    }

}
