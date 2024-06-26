package com.example.hust_learning_server.controller;

import java.util.List;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import com.example.hust_learning_server.dto.request.AddExamsForUserReq;
import com.example.hust_learning_server.dto.request.ExamReq;
import com.example.hust_learning_server.dto.request.ExamSavedReq;
import com.example.hust_learning_server.dto.request.ExamScoringReq;
import com.example.hust_learning_server.dto.request.SearchExamReq;
import com.example.hust_learning_server.dto.response.MessageResponse;
import com.example.hust_learning_server.service.ExamService;
import com.example.hust_learning_server.utils.PageUtils;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/exams")
@RequiredArgsConstructor
public class ExamController {
    private final ExamService examService;

    @PostMapping
    public ResponseEntity<MessageResponse> addExam(@RequestBody ExamReq examReq) {
        MessageResponse ms = new MessageResponse();
        examService.addExam(examReq);
        return ResponseEntity.ok(ms);
    }

    @PostMapping("/exams-for-user")
    public ResponseEntity<MessageResponse> addExamsForUser(@RequestBody AddExamsForUserReq addExamsForUserReq) {
        MessageResponse ms = new MessageResponse();
        examService.addExamsForUser(addExamsForUserReq.getExamIds(), addExamsForUserReq.getUserId());
        return ResponseEntity.ok(ms);
    }

    @PostMapping("/all-exams")
    public ResponseEntity<MessageResponse> getAllExams(
        @RequestBody SearchExamReq searchExamReq
    ) {
        MessageResponse ms = new MessageResponse();
        Pageable pageable = PageUtils.getPageable(searchExamReq.getPage(), searchExamReq.getSize(), searchExamReq.getOrderBy(), searchExamReq.isAscending());
        ms.data = examService.getAllExams(searchExamReq.getClassRoomId(), searchExamReq.getIsPrivate(), searchExamReq.getNameSearch(), pageable);
        return ResponseEntity.ok(ms);
    }

    @GetMapping("/all-exams-of-user")
    public ResponseEntity<MessageResponse> getAllExamsForUser(
        @RequestParam(required = true, defaultValue = "0") int page,
        @RequestParam(required = true, defaultValue = "10") int size
    ) {
        MessageResponse ms = new MessageResponse();
        Pageable pageable = PageUtils.getPageable(page,size,null,false);
        ms.data = examService.getAllExamsForUser(pageable);
        return ResponseEntity.ok(ms);
    }

    @GetMapping("/{id}")
    public ResponseEntity<MessageResponse> getAllExamsForUser(
        @PathVariable("id") long id
    ) {
        MessageResponse ms = new MessageResponse();
        ms.data = examService.getExamById(id);
        return ResponseEntity.ok(ms);
    }

    @PostMapping("/exam-scoring")
    public ResponseEntity<MessageResponse> examScoring(@RequestBody ExamScoringReq examScoringReq) {
        MessageResponse ms = new MessageResponse();
        examService.examScoring(examScoringReq);
        return ResponseEntity.ok(ms);
    }

    @PostMapping("/exam-saved")
    public ResponseEntity<MessageResponse> examSaved(@RequestBody List<ExamSavedReq> examSavedReqs) {
        MessageResponse ms = new MessageResponse();
        examService.examSaved(examSavedReqs);
        return ResponseEntity.ok(ms);
    }

    @GetMapping("/exam-saved/{examId}")
    public ResponseEntity<MessageResponse> getExamSaved(@PathVariable("examId") long examId) {
        MessageResponse ms = new MessageResponse();
        ms.data = examService.getExamSaved(examId);
        return ResponseEntity.ok(ms);
    }

    @DeleteMapping("/delete-exam-of-user/{examId}")
    public ResponseEntity<MessageResponse> deleteExamOfUser(@PathVariable("examId") long examId) {
        MessageResponse ms = new MessageResponse();
        examService.deleteExamOfUser(examId);
        return ResponseEntity.ok(ms);
    }

    @DeleteMapping("/{examId}")
    public ResponseEntity<MessageResponse> deleteExam(@PathVariable("examId") long examId) {
        MessageResponse ms = new MessageResponse();
        examService.deleteExam(examId);
        return ResponseEntity.ok(ms);
    }

}
