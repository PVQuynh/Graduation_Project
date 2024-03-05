package com.example.HustLearning.mapper;

import com.example.HustLearning.dto.AnswerDTO;
import com.example.HustLearning.entity.Answer;

import java.util.List;

public interface AnswerMapper {
    Answer toEntity(AnswerDTO dto);
    AnswerDTO toDTO(Answer entity);
    List<AnswerDTO> toDTOList(List<Answer> entityList);
    List<Answer> toEntityList(List<AnswerDTO> dtoList);
}
