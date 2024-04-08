package com.example.hust_learning_server.mapper.Impl;

import com.example.hust_learning_server.dto.request.VocabularyMediumReq;
import com.example.hust_learning_server.dto.request.VocabularyReq;
import com.example.hust_learning_server.dto.response.VocabularyMediumRes;
import com.example.hust_learning_server.dto.response.VocabularyRes;
import com.example.hust_learning_server.entity.Topic;
import com.example.hust_learning_server.entity.Vocabulary;
import com.example.hust_learning_server.entity.VocabularyMedium;
import com.example.hust_learning_server.mapper.VocabularyMapper;
import com.example.hust_learning_server.mapper.VocabularyMediumMapper;
import com.example.hust_learning_server.repository.TopicRepository;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;
@Component
@RequiredArgsConstructor
public class VocabularyMapperImpl implements VocabularyMapper {

    private final TopicRepository topicRepository;
    private final VocabularyMediumMapper vocabularyMediumMapper;

    @Override
    public Vocabulary toEntity(VocabularyReq dto) {
        ModelMapper modelMapper = new ModelMapper();
        Vocabulary vocabulary = modelMapper.map(dto, Vocabulary.class);

        List<VocabularyMediumReq> vocabularyReqs = dto.getVocabularyMediumReqs();
        List<VocabularyMedium> vocabularyMedia = vocabularyMediumMapper.toEntityList(vocabularyReqs);

        vocabularyMedia.forEach(vocabularyMedium -> vocabularyMedium.setVocabulary(vocabulary));
        vocabulary.setVocabularyMedia(vocabularyMedia);

        Topic topic = topicRepository.findById(dto.getTopicId()).get();
        vocabulary.setTopic(topic);

        return vocabulary;
    }

    @Override
    public VocabularyRes toDTO(Vocabulary entity) {
        ModelMapper modelMapper = new ModelMapper();
        VocabularyRes vocabularyRes = modelMapper.map(entity, VocabularyRes.class);

        List<VocabularyMedium> vocabularyMedia = entity.getVocabularyMedia();
        List<VocabularyMediumRes> vocabularyMediumResList = vocabularyMediumMapper.toDTOList(vocabularyMedia);

        vocabularyRes.setVocabularyMediumRes(vocabularyMediumResList);

        vocabularyRes.setTopicId(entity.getTopic().getId());
        vocabularyRes.setTopicContent(entity.getTopic().getContent());

        return vocabularyRes;
    }

    @Override
    public List<VocabularyRes> toDTOList(List<Vocabulary> entityList) {
        return entityList.stream()
                .map(entity->toDTO(entity))
                .collect(Collectors.toList());
    }

    @Override
    public List<Vocabulary> toEntityList(List<VocabularyReq> dtoList) {
        return dtoList.stream()
                .map(dto->toEntity(dto))
                .collect(Collectors.toList());
    }

}
