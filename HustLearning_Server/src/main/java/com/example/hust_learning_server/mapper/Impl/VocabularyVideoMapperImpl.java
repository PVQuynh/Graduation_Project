package com.example.hust_learning_server.mapper.Impl;

import com.example.hust_learning_server.dto.request.VocabularyVideoReq;
import com.example.hust_learning_server.dto.response.VocabularyVideoRes;
import com.example.hust_learning_server.entity.Vocabulary;
import com.example.hust_learning_server.entity.VocabularyVideo;
import com.example.hust_learning_server.mapper.VocabularyVideoMapper;
import com.example.hust_learning_server.repository.VocabularyRepository;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
@RequiredArgsConstructor
public class VocabularyVideoMapperImpl implements VocabularyVideoMapper {

    private final VocabularyRepository vocabularyRepository;

    @Override
    public VocabularyVideo toEntity(VocabularyVideoReq dto) {
        ModelMapper modelMapper = new ModelMapper();
        VocabularyVideo vocabularyVideo = modelMapper.map(dto, VocabularyVideo.class);
        vocabularyVideo.setId(0);

        Vocabulary vocabulary = vocabularyRepository.findById(dto.getVocabularyId()).orElse(null);
        vocabularyVideo.setVocabulary(vocabulary);

        return vocabularyVideo;
    }

    @Override
    public VocabularyVideoRes toDTO(VocabularyVideo entity) {
        ModelMapper modelMapper = new ModelMapper();
        VocabularyVideoRes vocabularyVideoRes = modelMapper.map(entity, VocabularyVideoRes.class);

        vocabularyVideoRes.setVocabularyVideoId(entity.getId());// have issue, need to fix

        vocabularyVideoRes.setVocabularyId(entity.getVocabulary().getId());
        vocabularyVideoRes.setVocabularyContent(entity.getVocabulary().getContent());

        return vocabularyVideoRes;
    }

    @Override
    public List<VocabularyVideo> toEntityList(List<VocabularyVideoReq> dtoList) {
        return dtoList.stream().map(dto->toEntity(dto)).collect(Collectors.toList());
    }

    @Override
    public List<VocabularyVideoRes> toDTOList(List<VocabularyVideo> entityList) {
        return entityList.stream().map(entity->toDTO(entity)).collect(Collectors.toList());
    }
}
