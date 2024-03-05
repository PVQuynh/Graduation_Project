package com.example.HustLearning.service;

import com.example.HustLearning.dto.request.QuestionReq;
import com.example.HustLearning.dto.request.QuestionSearchParam;
import com.example.HustLearning.dto.response.QuestionRes;
import com.example.HustLearning.entity.Question;

import java.util.List;

public interface QuestionService {


    void addQuestion(QuestionReq question);


    void deleteQuestionById(long id);

    List<QuestionRes> getQuestionsByTopicId(long topicId);

    List<QuestionRes> searchQuestion(QuestionSearchParam searchParam);
}
