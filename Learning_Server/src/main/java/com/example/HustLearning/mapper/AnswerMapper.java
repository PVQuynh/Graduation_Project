package com.example.HustLearning.mapper;

import com.example.HustLearning.dto.request.AnswerReq;
import com.example.HustLearning.dto.response.AnswerRes;
import com.example.HustLearning.entity.Answer;

import java.util.List;

public interface AnswerMapper {
    Answer toEntity(AnswerReq dto);

    AnswerRes toDTO(Answer entity);

    List<AnswerRes> toDTOList(List<Answer> entityList);

    List<Answer> toEntityList(List<AnswerReq> dtoList);
}
