package com.example.hust_learning_server.mapper.Impl;

import com.example.hust_learning_server.dto.request.AnswerReq;
import com.example.hust_learning_server.dto.response.AnswerRes;
import com.example.hust_learning_server.dto.request.QuestionReq;
import com.example.hust_learning_server.dto.response.QuestionRes;
import com.example.hust_learning_server.entity.Answer;
import com.example.hust_learning_server.entity.ClassRoom;
import com.example.hust_learning_server.entity.Question;
import com.example.hust_learning_server.mapper.AnswerMapper;
import com.example.hust_learning_server.mapper.QuestionMapper;
import com.example.hust_learning_server.repository.ClassRoomRepository;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@Component
@RequiredArgsConstructor
public class QuestionMapperImpl implements QuestionMapper {

    private final AnswerMapper anwserMapper;
    private final ClassRoomRepository classRoomRepository;

    @Override
    public Question toEntity(QuestionReq dto) {
        ModelMapper modelMapper = new ModelMapper();
        Question question = modelMapper.map(dto,Question.class);

        List<AnswerReq> answerReqs = dto.getAnswerReqs();
        List<Answer> answers = anwserMapper.toEntityList(answerReqs);
        answers.forEach(answer -> answer.setQuestion(question));
        question.setAnswers(answers);
        return question;
    }

    @Override
    public QuestionRes toDTO(Question entity) {
        ModelMapper modelMapper = new ModelMapper();
        QuestionRes questionRes = modelMapper.map(entity, QuestionRes.class);

        List<Answer> answers = entity.getAnswers();
        List<AnswerRes> answerResList = anwserMapper.toDTOList(answers);
        questionRes.setQuestionId(entity.getId());
        questionRes.setAnswerResList(answerResList);
        ClassRoom classRoom = classRoomRepository.findById(entity.getClassRoomId()).orElse(null);
        if (Objects.nonNull(classRoom)) {
            questionRes.setClassRoomContent(classRoom.getContent());
        }

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
