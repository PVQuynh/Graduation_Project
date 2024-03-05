package com.example.HustLearning.mapper.Impl;

import com.example.HustLearning.dto.AnswerDTO;
import com.example.HustLearning.dto.request.QuestionReq;
import com.example.HustLearning.dto.response.QuestionRes;
import com.example.HustLearning.entity.Answer;
import com.example.HustLearning.entity.Question;
import com.example.HustLearning.mapper.QuestionMapper;
import com.example.HustLearning.repository.TopicRepository;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.modelmapper.TypeMap;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
@RequiredArgsConstructor
public class QuestionMapperImpl implements QuestionMapper {

    private final AnwserMapper anwserMapper;
    private final TopicRepository topicRepository;

    @Override
    public Question toEntity(QuestionReq dto) {
        ModelMapper modelMapper = new ModelMapper();

        TypeMap<QuestionRes, Question> typeMap =  modelMapper.createTypeMap(QuestionRes.class,Question.class);
        List<AnswerDTO> answerDTOS = dto.getAnswerDTOS();
        List<Answer> answers = anwserMapper.toEntityList(answerDTOS);
        Question question = modelMapper.map(dto,Question.class);
        answers.forEach(answer -> answer.setQuestion(question));
        long topicId = dto.getTopic_id();
        question.setAnswers(answers);
        question.setTopic(topicRepository.findById(topicId).get());

        return question;
    }

    @Override
    public QuestionRes toDTO(Question entity) {
        ModelMapper modelMapper = new ModelMapper();

        TypeMap<Question, QuestionRes> typeMap =  modelMapper.createTypeMap(Question.class, QuestionRes.class);
        List<Answer> answers = entity.getAnswers();
        List<AnswerDTO> answerDTOList = anwserMapper.toDTOList(answers);

        QuestionRes questionRes = modelMapper.map(entity, QuestionRes.class);
        questionRes.setId(entity.getId());
        questionRes.setAnswerDTOS(answerDTOList);
        questionRes.setTopic_id(entity.getTopic().getId());

        return questionRes;
    }

    @Override
    public List<QuestionRes> toDTOList(List<Question> entityList) {
        return entityList.stream().map(entity->toDTO(entity)).collect(Collectors.toList());
    }

    @Override
    public List<Question> toEntityList(List<QuestionReq> dtoList) {
        return dtoList.stream().map(dto->toEntity(dto)).collect(Collectors.toList());
    }

}
