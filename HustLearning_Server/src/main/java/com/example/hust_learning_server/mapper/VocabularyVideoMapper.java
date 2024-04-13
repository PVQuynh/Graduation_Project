package com.example.hust_learning_server.mapper;

import com.example.hust_learning_server.dto.request.VocabularyVideoReq;
import com.example.hust_learning_server.dto.response.VocabularyVideoRes;
import com.example.hust_learning_server.entity.VocabularyVideo;

import java.util.List;

public interface VocabularyVideoMapper {

    VocabularyVideo toEntity(VocabularyVideoReq dto);

    VocabularyVideoRes toDTO(VocabularyVideo entity);

    List<VocabularyVideo> toEntityList(List<VocabularyVideoReq> dtoList);

    List<VocabularyVideoRes> toDTOList(List<VocabularyVideo> entityList);

}
