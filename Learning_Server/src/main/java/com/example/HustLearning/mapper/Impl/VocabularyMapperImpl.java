package com.example.HustLearning.mapper.Impl;

import com.example.HustLearning.dto.request.VocabReq;
import com.example.HustLearning.dto.response.VocabRes;
import com.example.HustLearning.entity.Topic;
import com.example.HustLearning.entity.Vocabulary;
import com.example.HustLearning.mapper.VocabMapper;
import com.example.HustLearning.repository.TopicRepository;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;
@Component
@RequiredArgsConstructor
public class VocabularyMapperImpl implements VocabMapper {

    private final TopicRepository topicRepository;

    @Override
    public Vocabulary toEntity(VocabReq dto) {
        ModelMapper modelMapper = new ModelMapper();
        Vocabulary vocabulary = modelMapper.map(dto, Vocabulary.class);

        long topicId = dto.getTopic_id();
        Topic topic = topicRepository.findById(topicId).get();
        vocabulary.setTopic(topic);

        return vocabulary;
    }

    @Override
    public VocabRes toDTO(Vocabulary entity) {
        ModelMapper modelMapper = new ModelMapper();
        VocabRes vocabularyDTO = modelMapper.map(entity, VocabRes.class);

        long topicId = entity.getTopic().getId();
        vocabularyDTO.setTopic_id(topicId);

        return vocabularyDTO;
    }

    @Override
    public List<VocabRes> toDTOList(List<Vocabulary> entityList) {
        return entityList.stream().map(entity->toDTO(entity)).collect(Collectors.toList());
    }

    @Override
    public List<Vocabulary> toEntityList(List<VocabReq> dtoList) {
        return dtoList.stream().map(dto->toEntity(dto)).collect(Collectors.toList());
    }

}
