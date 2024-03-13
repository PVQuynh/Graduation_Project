package com.example.HustLearning.mapper;

import com.example.HustLearning.dto.request.VocabularyReq;
import com.example.HustLearning.dto.response.VocabularyRes;
import com.example.HustLearning.entity.Vocabulary;

import java.util.List;

public interface VocabMapper {

    Vocabulary toEntity(VocabularyReq dto);

    VocabularyRes toDTO(Vocabulary entity);

    List<VocabularyRes> toDTOList(List<Vocabulary> entityList);

    List<Vocabulary> toEntityList(List<VocabularyReq> dtoList);
}
