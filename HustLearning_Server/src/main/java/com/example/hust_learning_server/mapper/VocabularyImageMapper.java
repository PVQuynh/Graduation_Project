package com.example.hust_learning_server.mapper;

import com.example.hust_learning_server.dto.request.VocabularyImageReq;
import com.example.hust_learning_server.dto.response.VocabularyImageRes;
import com.example.hust_learning_server.entity.VocabularyImage;

import java.util.List;

public interface VocabularyImageMapper {

    VocabularyImage toEntity(VocabularyImageReq dto);

    VocabularyImageRes toDTO(VocabularyImage entity);

    List<VocabularyImage> toEntityList(List<VocabularyImageReq> dtoList);

    List<VocabularyImageRes> toDTOList(List<VocabularyImage> entityList);

}
