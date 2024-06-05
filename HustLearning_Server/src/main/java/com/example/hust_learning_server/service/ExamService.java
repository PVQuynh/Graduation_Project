package com.example.hust_learning_server.service;

import java.util.List;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import com.example.hust_learning_server.dto.request.ExamReq;
import com.example.hust_learning_server.dto.request.ExamScoringReq;
import com.example.hust_learning_server.dto.response.ExamRes;
import com.example.hust_learning_server.dto.response.ExamResForUser;

public interface ExamService {

    void addExam(ExamReq examReq);

    void addExamsForUser(List<Long> examIds);

    void examScoring(ExamScoringReq examScoringReq);

    Page<ExamRes> getAllExams(long topicId, boolean isPrivate, String nameSearch, Pageable pageable);

    List<ExamResForUser> getAllExamsForUser();

    void deleteExamOfUser(long examId);

    void deleteExam(long examId);
}
