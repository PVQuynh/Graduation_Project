package com.example.hust_learning_server.mapper;

import com.example.hust_learning_server.dto.request.VocabularyMediumReq;
import com.example.hust_learning_server.dto.response.VocabularyMediumRes;
import com.example.hust_learning_server.entity.VocabularyMedium;

import java.util.List;

public interface VocabularyMediumMapper {
    VocabularyMedium toEntity(VocabularyMediumReq dto);

    VocabularyMediumRes toDTO(VocabularyMedium entity);

    List<VocabularyMedium> toEntityList(List<VocabularyMediumReq> dtoList);

    List<VocabularyMediumRes> toDTOList(List<VocabularyMedium> entityList);

}
