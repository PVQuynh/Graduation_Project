package com.example.hust_learning_server.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import com.example.hust_learning_server.dto.request.AddExamsForUserReq;
import com.example.hust_learning_server.dto.request.ExamReq;
import com.example.hust_learning_server.dto.response.MessageResponse;
import com.example.hust_learning_server.service.ExamService;
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

    @PostMapping("exams-for-user")
    public ResponseEntity<MessageResponse> addExamsForUser(@RequestBody AddExamsForUserReq addExamsForUserReq) {
        MessageResponse ms = new MessageResponse();
        examService.addExamsForUser(addExamsForUserReq.getExamIds());
        return ResponseEntity.ok(ms);
    }

    @GetMapping("all-exams")
    public ResponseEntity<MessageResponse> getAllExams(
        @RequestParam(required = true) long topicId,
        @RequestParam(required = false, defaultValue = "false") boolean isPrivate
    ) {
        MessageResponse ms = new MessageResponse();
        ms.data = examService.getAllExams(topicId, isPrivate);
        return ResponseEntity.ok(ms);
    }

    @GetMapping("all-exams-for-user")
    public ResponseEntity<MessageResponse> getAllExamsForUser() {
        MessageResponse ms = new MessageResponse();
        ms.data = examService.getAllExamsForUser();
        return ResponseEntity.ok(ms);
    }
}
