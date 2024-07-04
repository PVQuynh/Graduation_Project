package com.example.hust_learning_server.service.Impl;

import com.example.hust_learning_server.dto.request.AnswerReq;
import com.example.hust_learning_server.dto.request.UpdateAnswerReq;
import com.example.hust_learning_server.entity.Answer;
import com.example.hust_learning_server.exception.ResourceNotFoundException;
import com.example.hust_learning_server.exception.UnAuthorizedException;
import com.example.hust_learning_server.mapper.AnswerMapper;
import com.example.hust_learning_server.repository.AnswerRepository;
import com.example.hust_learning_server.service.AnswerService;
import com.example.hust_learning_server.utils.EmailUtils;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.util.ObjectUtils;

@Service
@RequiredArgsConstructor
public class AnswerServiceImpl implements AnswerService {

    private final AnswerRepository answerRepository;

    private final AnswerMapper answerMapper;

    @Override
    public void addAnswer(AnswerReq answerReq) {
        String email = EmailUtils.getCurrentUser();
        if (ObjectUtils.isEmpty(email)) {
            throw new UnAuthorizedException();
        }

        Answer answer = answerMapper.toEntity(answerReq);
        answerRepository.save(answer);
    }

    @Override
    public void updateAnswer(UpdateAnswerReq updateAnswerReq) {
        String email = EmailUtils.getCurrentUser();
        if (ObjectUtils.isEmpty(email)) {
            throw new UnAuthorizedException();
        }
        Answer answer = answerRepository.findById(updateAnswerReq.getAnswerId()).orElseThrow(ResourceNotFoundException::new);
        if (updateAnswerReq.getContent() != null) {
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

    @Override
    public void deleteAnswer(long id) {
        String email = EmailUtils.getCurrentUser();
        if (ObjectUtils.isEmpty(email)) {
            throw new UnAuthorizedException();
        }
        Answer answer = answerRepository.findById(id).orElseThrow(ResourceNotFoundException::new);
        answerRepository.delete(answer);
    }

}
