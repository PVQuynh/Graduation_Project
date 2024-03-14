package com.example.HustLearning.service;

import com.example.HustLearning.dto.request.AnswerReq;
import com.example.HustLearning.dto.request.UpdateAnswerReq;
import com.example.HustLearning.entity.Answer;
import org.springframework.stereotype.Service;

import java.util.List;

public interface AnswerService {

    void addAnswer (AnswerReq answerReq);

    void updateAnswer (UpdateAnswerReq updateAnswerReq);

    void deleteAnswer (long id);

}
