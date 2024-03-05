package com.example.HustLearning.mapper.Impl;

import com.example.HustLearning.dto.AnswerDTO;
import com.example.HustLearning.entity.Answer;
import com.example.HustLearning.mapper.AnswerMapper;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
@RequiredArgsConstructor
public class AnwserMapper implements AnswerMapper {


    @Override
    public Answer toEntity(AnswerDTO dto) {
        ModelMapper modelMapper = new ModelMapper();
        Answer answer = modelMapper.map(dto, Answer.class);

        return answer;
    }

    @Override
    public AnswerDTO toDTO(Answer entity) {
        ModelMapper modelMapper = new ModelMapper();
        AnswerDTO answerDTO = modelMapper.map(entity,AnswerDTO.class);

        return answerDTO;
    }

    @Override
    public List<AnswerDTO> toDTOList(List<Answer> entityList) {
        return entityList.stream().map(entity->toDTO(entity)).collect(Collectors.toList());
    }

    @Override
    public List<Answer> toEntityList(List<AnswerDTO> dtoList) {
        return dtoList.stream().map(dto->toEntity(dto)).collect(Collectors.toList());
    }

}
