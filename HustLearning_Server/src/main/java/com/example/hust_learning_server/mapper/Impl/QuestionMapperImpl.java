package com.example.hust_learning_server.mapper.Impl;

import com.example.hust_learning_server.dto.request.AnswerReq;
import com.example.hust_learning_server.dto.response.AnswerRes;
import com.example.hust_learning_server.dto.request.QuestionReq;
import com.example.hust_learning_server.dto.response.QuestionRes;
import com.example.hust_learning_server.entity.Answer;
import com.example.hust_learning_server.entity.Question;
import com.example.hust_learning_server.mapper.AnswerMapper;
import com.example.hust_learning_server.mapper.QuestionMapper;
import com.example.hust_learning_server.repository.TopicRepository;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
@RequiredArgsConstructor
public class QuestionMapperImpl implements QuestionMapper {

    private final AnswerMapper anwserMapper;
    private final TopicRepository topicRepository;

    @Override
    public Question toEntity(QuestionReq dto) {
        ModelMapper modelMapper = new ModelMapper();

        List<AnswerReq> answerReqs = dto.getAnswerReqs();
        List<Answer> answers = anwserMapper.toEntityList(answerReqs);

        Question question = modelMapper.map(dto,Question.class);
        answers.forEach(answer -> answer.setQuestion(question));

        long topicId = dto.getTopicId();

        question.setAnswers(answers);
        question.setTopic(topicRepository.findById(topicId).get());

        return question;
    }

    @Override
    public QuestionRes toDTO(Question entity) {
        ModelMapper modelMapper = new ModelMapper();

        List<Answer> answers = entity.getAnswers();
        List<AnswerRes> answerResList = anwserMapper.toDTOList(answers);

        QuestionRes questionRes = modelMapper.map(entity, QuestionRes.class);
        questionRes.setQuestionId(entity.getId());
        questionRes.setAnswerResList(answerResList);
        questionRes.setTopicId(entity.getTopic().getId());

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
