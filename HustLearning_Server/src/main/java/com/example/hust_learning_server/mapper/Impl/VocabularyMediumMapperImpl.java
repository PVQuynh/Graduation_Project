package com.example.hust_learning_server.mapper.Impl;

import com.example.hust_learning_server.dto.request.VocabularyMediumReq;
import com.example.hust_learning_server.dto.response.VocabularyMediumRes;
import com.example.hust_learning_server.entity.Topic;
import com.example.hust_learning_server.entity.Vocabulary;
import com.example.hust_learning_server.entity.VocabularyMedium;
import com.example.hust_learning_server.mapper.VocabularyMediumMapper;
import com.example.hust_learning_server.repository.VocabularyRepository;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
@RequiredArgsConstructor
public class VocabularyMediumMapperImpl implements VocabularyMediumMapper {

    private final VocabularyRepository vocabularyRepository;

    @Override
    public VocabularyMedium toEntity(VocabularyMediumReq dto) {
        ModelMapper modelMapper = new ModelMapper();
        VocabularyMedium vocabularyMedium = modelMapper.map(dto, VocabularyMedium.class);

        Vocabulary vocabulary = vocabularyRepository.findById(dto.getVocabularyId()).orElse(null);
        vocabularyMedium.setVocabulary(vocabulary);

        return vocabularyMedium;
    }

    @Override
    public VocabularyMediumRes toDTO(VocabularyMedium entity) {
        ModelMapper modelMapper = new ModelMapper();
        VocabularyMediumRes vocabularyMediumRes = modelMapper.map(entity, VocabularyMediumRes.class);

        vocabularyMediumRes.setVocabularyMediumId(entity.getId());// have issue, need to fix

        vocabularyMediumRes.setVocabularyId(entity.getVocabulary().getId());
        vocabularyMediumRes.setVocabularyContent(entity.getVocabulary().getContent());

        return vocabularyMediumRes;
    }

    @Override
    public List<VocabularyMedium> toEntityList(List<VocabularyMediumReq> dtoList) {
        return dtoList.stream().map(dto->toEntity(dto)).collect(Collectors.toList());
    }

    @Override
    public List<VocabularyMediumRes> toDTOList(List<VocabularyMedium> entityList) {
        return entityList.stream().map(entity->toDTO(entity)).collect(Collectors.toList());
    }
}
