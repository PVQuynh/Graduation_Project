package com.example.HustLearning.mapper.Impl;

import com.example.HustLearning.dto.request.AnswerReq;
import com.example.HustLearning.dto.response.AnswerRes;
import com.example.HustLearning.entity.Question;
import com.example.HustLearning.entity.Topic;
import com.example.HustLearning.entity.Answer;
import com.example.HustLearning.exception.BusinessLogicException;
import com.example.HustLearning.mapper.AnswerMapper;
import com.example.HustLearning.mapper.AnswerMapper;
import com.example.HustLearning.repository.QuestionRepository;
import com.example.HustLearning.repository.TopicRepository;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
@RequiredArgsConstructor
public class AnswerMapperImpl implements AnswerMapper {

    private final QuestionRepository questionRepository;

    @Override
    public Answer toEntity(AnswerReq dto) {
        ModelMapper modelMapper = new ModelMapper();
        Answer answer = modelMapper.map(dto, Answer.class);

        Question question = questionRepository.findById(dto.getQuestionId()).orElseThrow(BusinessLogicException::new);
        answer.setQuestion(question);

        return answer;
    }

    @Override
    public AnswerRes toDTO(Answer entity) {
        ModelMapper modelMapper = new ModelMapper();
        AnswerRes answerDTO = modelMapper.map(entity, AnswerRes.class);

        answerDTO.setQuestionId(entity.getQuestion().getId());

        return answerDTO;
    }

    @Override
    public List<AnswerRes> toDTOList(List<Answer> entityList) {
        return entityList.stream().map(entity->toDTO(entity)).collect(Collectors.toList());
    }

    @Override
    public List<Answer> toEntityList(List<AnswerReq> dtoList) {
        return dtoList.stream().map(dto->toEntity(dto)).collect(Collectors.toList());
    }

}
