package com.example.learning_server.mapper;

import com.example.learning_server.dto.request.AnswerReq;
import com.example.learning_server.dto.response.AnswerRes;
import com.example.learning_server.entity.Answer;

import java.util.List;

public interface AnswerMapper {
    Answer toEntity(AnswerReq dto);

    AnswerRes toDTO(Answer entity);

    List<AnswerRes> toDTOList(List<Answer> entityList);

    List<Answer> toEntityList(List<AnswerReq> dtoList);
}
