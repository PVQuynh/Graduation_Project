package com.example.hust_learning_server.service;

import com.example.hust_learning_server.dto.request.DeleteQuestionsReq;
import com.example.hust_learning_server.dto.request.QuestionReq;
import com.example.hust_learning_server.dto.request.QuestionLimitReq;
import com.example.hust_learning_server.dto.request.UpdateQuestionReq;
import com.example.hust_learning_server.dto.response.QuestionRes;

import java.util.List;

public interface QuestionService {

    QuestionRes getQuestionById(long id);

    List<QuestionRes> getAllQuestions(long classRoomId, String contentSearch);

    List<QuestionRes> getQuestionsByClassRoomId(long classRoomId);

    List<QuestionRes> getQuestionsByExamId(long examId);

    List<QuestionRes> questionLimits(QuestionLimitReq questionLimitReq);

    List<QuestionRes> questionLimits_v2(int page, int size, long classRoomId);

    void addQuestion(QuestionReq question);

    void addListQuestions(List<QuestionReq> questionReqList);

    void updateQuestion(UpdateQuestionReq updateQuestionReq);

    void deleteQuestionById(long id);

    void deleteQuestions(DeleteQuestionsReq deleteQuestionsReq);
}
