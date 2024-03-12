package com.example.HustLearning.service;

import com.example.HustLearning.dto.request.QuestionReq;
import com.example.HustLearning.dto.request.QuestionLimitReq;
import com.example.HustLearning.dto.request.UpdateQuestionReq;
import com.example.HustLearning.dto.response.QuestionRes;

import java.util.List;

public interface QuestionService {

    List<QuestionRes> getQuestionsByTopicId(long topicId);

    List<QuestionRes> questionLimits(QuestionLimitReq searchParam);

    void addQuestion(QuestionReq question);

    void updateQuestion(UpdateQuestionReq updateQuestionReq);

    void deleteQuestionById(long id);


}
