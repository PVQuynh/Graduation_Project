package com.example.HustLearning.mapper;

import com.example.HustLearning.dto.request.VocabReq;
import com.example.HustLearning.dto.response.VocabRes;
import com.example.HustLearning.entity.Vocabulary;

import java.util.List;

public interface VocabMapper {
    Vocabulary toEntity(VocabReq dto);

    VocabRes toDTO(Vocabulary entity);

    List<VocabRes> toDTOList(List<Vocabulary> entityList);

    List<Vocabulary> toEntityList(List<VocabReq> dtoList);
}
