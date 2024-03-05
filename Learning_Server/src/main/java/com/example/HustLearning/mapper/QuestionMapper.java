package com.example.HustLearning.mapper;

import com.example.HustLearning.dto.request.QuestionReq;
import com.example.HustLearning.dto.response.QuestionRes;
import com.example.HustLearning.entity.Question;

import java.util.List;

public interface QuestionMapper {
    Question toEntity(QuestionReq dto);
    QuestionRes toDTO(Question entity);

    List<QuestionRes> toDTOList(List<Question> entityList);

    List<Question> toEntityList(List<QuestionReq> dtoList);
}
