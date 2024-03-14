package com.example.learning_server.mapper;

import com.example.learning_server.dto.request.VocabularyReq;
import com.example.learning_server.dto.response.VocabularyRes;
import com.example.learning_server.entity.Vocabulary;

import java.util.List;

public interface VocabMapper {

    Vocabulary toEntity(VocabularyReq dto);

    VocabularyRes toDTO(Vocabulary entity);

    List<VocabularyRes> toDTOList(List<Vocabulary> entityList);

    List<Vocabulary> toEntityList(List<VocabularyReq> dtoList);
}
