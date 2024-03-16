package com.example.hust_learning_server.service;

import com.example.hust_learning_server.dto.request.QuestionReq;
import com.example.hust_learning_server.dto.request.QuestionLimitReq;
import com.example.hust_learning_server.dto.request.UpdateQuestionReq;
import com.example.hust_learning_server.dto.response.QuestionRes;

import java.util.List;

public interface QuestionService {

    List<QuestionRes> getQuestionsByTopicId(long topicId);

    List<QuestionRes> questionLimits(QuestionLimitReq questionLimitReq);

    void addQuestion(QuestionReq question);

    void updateQuestion(UpdateQuestionReq updateQuestionReq);

    void deleteQuestionById(long id);


}
