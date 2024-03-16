package com.example.learning_server.service;

import com.example.learning_server.dto.request.AnswerReq;
import com.example.learning_server.dto.request.UpdateAnswerReq;

public interface AnswerService {

    void addAnswer (AnswerReq answerReq);

    void updateAnswer (UpdateAnswerReq updateAnswerReq);

    void deleteAnswer (long id);

}
