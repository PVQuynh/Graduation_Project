package com.example.hust_learning_server.service;

import java.util.List;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import com.example.hust_learning_server.dto.request.ExamReq;
import com.example.hust_learning_server.dto.request.ExamSavedReq;
import com.example.hust_learning_server.dto.request.ExamScoringReq;
import com.example.hust_learning_server.dto.response.ExamRes;
import com.example.hust_learning_server.dto.response.ExamResForUser;
import com.example.hust_learning_server.dto.response.ExamSavedRes;

public interface ExamService {

    void addExam(ExamReq examReq);

    void addExamsForUser(List<Long> examIds, long userId);

    void examScoring(ExamScoringReq examScoringReq);

    void examSaved(List<ExamSavedReq> examSavedReqs);

    List<ExamSavedRes> getExamSaved(long examId);

    ExamRes getExamById(long id);

    Page<ExamRes> getAllExams(long classRoomId, String isPrivate, String nameSearch, Pageable pageable);

    Page<ExamResForUser> getAllExamsForUser(Pageable pageable);

    void deleteExamOfUser(long examId);

    void deleteExam(long examId);
}
