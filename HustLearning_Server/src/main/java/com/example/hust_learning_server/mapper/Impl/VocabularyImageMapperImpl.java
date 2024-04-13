package com.example.hust_learning_server.mapper.Impl;

import com.example.hust_learning_server.dto.request.VocabularyImageReq;
import com.example.hust_learning_server.dto.response.VocabularyImageRes;
import com.example.hust_learning_server.entity.Vocabulary;
import com.example.hust_learning_server.entity.VocabularyImage;
import com.example.hust_learning_server.mapper.VocabularyImageMapper;
import com.example.hust_learning_server.repository.VocabularyRepository;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
@RequiredArgsConstructor
public class VocabularyImageMapperImpl implements VocabularyImageMapper {

    private final VocabularyRepository vocabularyRepository;

    @Override
    public VocabularyImage toEntity(VocabularyImageReq dto) {
        ModelMapper modelMapper = new ModelMapper();
        VocabularyImage vocabularyImage = modelMapper.map(dto, VocabularyImage.class);
        vocabularyImage.setId(0);

        Vocabulary vocabulary = vocabularyRepository.findById(dto.getVocabularyId()).orElse(null);
        vocabularyImage.setVocabulary(vocabulary);

        return vocabularyImage;
    }

    @Override
    public VocabularyImageRes toDTO(VocabularyImage entity) {
        ModelMapper modelMapper = new ModelMapper();
        VocabularyImageRes vocabularyImageRes = modelMapper.map(entity, VocabularyImageRes.class);

        vocabularyImageRes.setVocabularyImageId(entity.getId());// have issue, need to fix

        vocabularyImageRes.setVocabularyId(entity.getVocabulary().getId());
        vocabularyImageRes.setVocabularyContent(entity.getVocabulary().getContent());

        return vocabularyImageRes;
    }

    @Override
    public List<VocabularyImage> toEntityList(List<VocabularyImageReq> dtoList) {
        return dtoList.stream().map(dto->toEntity(dto)).collect(Collectors.toList());
    }

    @Override
    public List<VocabularyImageRes> toDTOList(List<VocabularyImage> entityList) {
        return entityList.stream().map(entity->toDTO(entity)).collect(Collectors.toList());
    }
}
