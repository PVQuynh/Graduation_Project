package com.example.HustLearning.mapper.Impl;

import com.example.HustLearning.dto.request.VocabularyReq;
import com.example.HustLearning.dto.response.VocabularyRes;
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
    public Vocabulary toEntity(VocabularyReq dto) {
        ModelMapper modelMapper = new ModelMapper();
        Vocabulary vocabulary = modelMapper.map(dto, Vocabulary.class);

        long topicId = dto.getTopicId();
        Topic topic = topicRepository.findById(topicId).get();
        vocabulary.setTopic(topic);

        return vocabulary;
    }

    @Override
    public VocabularyRes toDTO(Vocabulary entity) {
        ModelMapper modelMapper = new ModelMapper();
        VocabularyRes vocabularyDTO = modelMapper.map(entity, VocabularyRes.class);

        vocabularyDTO.setTopicId(entity.getTopic().getId());

        return vocabularyDTO;
    }

    @Override
    public List<VocabularyRes> toDTOList(List<Vocabulary> entityList) {
        return entityList.stream().map(entity->toDTO(entity)).collect(Collectors.toList());
    }

    @Override
    public List<Vocabulary> toEntityList(List<VocabularyReq> dtoList) {
        return dtoList.stream().map(dto->toEntity(dto)).collect(Collectors.toList());
    }

}
