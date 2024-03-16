package com.example.learning_server.mapper;

import com.example.learning_server.dto.request.QuestionReq;
import com.example.learning_server.dto.response.QuestionRes;
import com.example.learning_server.entity.Question;

import java.util.List;

public interface QuestionMapper {

    Question toEntity(QuestionReq dto);

    QuestionRes toDTO(Question entity);

    List<QuestionRes> toDTOList(List<Question> entityList);

    List<Question> toEntityList(List<QuestionReq> dtoList);
}
