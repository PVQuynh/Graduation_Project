package com.example.HustLearning.service.Impl;

import com.example.HustLearning.dto.request.AnswerReq;
import com.example.HustLearning.dto.request.UpdateAnswerReq;
import com.example.HustLearning.entity.Answer;
import com.example.HustLearning.exception.BusinessLogicException;
import com.example.HustLearning.mapper.AnswerMapper;
import com.example.HustLearning.repository.AnswerRepository;
import com.example.HustLearning.repository.QuestionRepository;
import com.example.HustLearning.service.AnswerService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class AnswerServiceImpl implements AnswerService {

    private final AnswerRepository answerRepository;


    private final AnswerMapper answerMapper;

    @Override
    public void addAnswer(AnswerReq answerReq) {

        Answer answer = answerMapper.toEntity(answerReq);

        answerRepository.save(answer);
    }

    @Override
    public void updateAnswer(UpdateAnswerReq updateAnswerReq) {
        Answer answer = answerRepository.findById(updateAnswerReq.getAnswerId()).orElse(null);

        if (answer != null) {
            if(updateAnswerReq.getContent() !=null) {
                answer.setContent(updateAnswerReq.getContent());
            }
            if (updateAnswerReq.getImageLocation() != null) {
                answer.setImageLocation(updateAnswerReq.getImageLocation());
            }
            if (updateAnswerReq.getVideoLocation() != null) {
                answer.setVideoLocation(updateAnswerReq.getVideoLocation());
            }
            answer.setCorrect(updateAnswerReq.isCorrect());

            answerRepository.save(answer);
        }
    }

    @Override
    public void deleteAnswer(long id) {
        answerRepository.deleteById(id);
    }
}
