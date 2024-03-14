package com.example.learning_server.service.Impl;

import com.example.learning_server.dto.request.AnswerReq;
import com.example.learning_server.dto.request.UpdateAnswerReq;
import com.example.learning_server.entity.Answer;
import com.example.learning_server.mapper.AnswerMapper;
import com.example.learning_server.repository.AnswerRepository;
import com.example.learning_server.service.AnswerService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

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
