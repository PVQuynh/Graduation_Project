package com.example.hust_learning_server.service;

import java.util.List;
import com.example.hust_learning_server.dto.request.ExamReq;
import com.example.hust_learning_server.dto.response.ExamRes;
import com.example.hust_learning_server.dto.response.ExamResForUser;

public interface ExamService {

    void addExam(ExamReq examReq);

    void addExamsForUser(List<Long> examIds);

    List<ExamRes> getAllExams(long topicId, boolean isPrivate);

    List<ExamResForUser> getAllExamsForUser();
}
