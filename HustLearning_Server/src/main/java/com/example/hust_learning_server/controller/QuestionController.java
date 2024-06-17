package com.example.hust_learning_server.controller;


import java.util.List;
import com.example.hust_learning_server.dto.request.DeleteQuestionsReq;
import com.example.hust_learning_server.dto.request.QuestionReq;
import com.example.hust_learning_server.dto.request.QuestionLimitReq;
import com.example.hust_learning_server.dto.request.UpdateQuestionReq;
import com.example.hust_learning_server.dto.response.MessageResponse;
import com.example.hust_learning_server.service.QuestionService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/questions")
public class QuestionController {

    private final QuestionService questionService;

    @GetMapping("/detail/{id}")
    public ResponseEntity<MessageResponse> getQuestionById(@PathVariable("id") long id) {
        MessageResponse ms = new MessageResponse();
        ms.data = questionService.getQuestionById(id);
        return ResponseEntity.ok(ms);

    }

    @GetMapping("/{classRoomId}")
    public ResponseEntity<MessageResponse> getAllQuestions(@PathVariable("classRoomId") long classRoomId) {
        MessageResponse ms = new MessageResponse();
        ms.data = questionService.getQuestionsByClassRoomId(classRoomId);
        return ResponseEntity.ok(ms);
    }

    @GetMapping("/all")
    public ResponseEntity<MessageResponse> getAllQuestions(
        @RequestParam(required = false, defaultValue = "0") long classRoomId,
        @RequestParam(required = false, defaultValue = "") String contentSearch
    ) {
        MessageResponse ms = new MessageResponse();
        ms.data = questionService.getAllQuestions(classRoomId, contentSearch);
        return ResponseEntity.ok(ms);
    }

    @GetMapping("/question-of-exam/{examId}")
    public ResponseEntity<MessageResponse> getAllQuestionsOfExam(@PathVariable("examId") long examId) {
        MessageResponse ms = new MessageResponse();
        ms.data = questionService.getQuestionsByExamId(examId);
        return ResponseEntity.ok(ms);

    }

    @PostMapping("/limits-classRoom")
    public ResponseEntity<MessageResponse> questionLimits(@RequestBody QuestionLimitReq questionLimitReq) {
        MessageResponse ms = new MessageResponse();
        ms.data = questionService.questionLimits(questionLimitReq);
        return ResponseEntity.ok(ms);

    }

    @GetMapping("/limits-classRoom/v2")
    public ResponseEntity<MessageResponse> questionLimits_v2(
            @RequestParam(defaultValue = "1", required = true) int page,
            @RequestParam(defaultValue = "10", required = true) int size,
            @RequestParam(required = true) long classRoomId
    ) {
        MessageResponse ms = new MessageResponse();
        ms.data = questionService.questionLimits_v2(page, size, classRoomId);
        return ResponseEntity.ok(ms);

    }

    @PostMapping
    public ResponseEntity<MessageResponse> addQuestion(@RequestBody QuestionReq questionReq) {
        MessageResponse ms = new MessageResponse();
        questionService.addQuestion(questionReq);
        return ResponseEntity.ok(ms);
    }

    @PostMapping("/add-list")
    public ResponseEntity<MessageResponse> addListQuestions(@RequestBody List<QuestionReq> questionReqList) {
        MessageResponse ms = new MessageResponse();
        questionService.addListQuestions(questionReqList);
        return ResponseEntity.ok(ms);
    }

    @PutMapping
    public ResponseEntity<MessageResponse> updateQuestion(@RequestBody UpdateQuestionReq updateQuestionReq) {
        MessageResponse ms = new MessageResponse();
        questionService.updateQuestion(updateQuestionReq);
        return ResponseEntity.ok(ms);

    }

    @DeleteMapping("/{id}")
    public ResponseEntity<MessageResponse> deleteQuestion(@PathVariable long id) {
        MessageResponse ms = new MessageResponse();
        questionService.deleteQuestionById(id);
        return ResponseEntity.ok(ms);
    }

    @DeleteMapping("/delete-list")
    public ResponseEntity<MessageResponse> deleteQuestions(
        @RequestBody DeleteQuestionsReq deleteQuestionsReq
    ) {
        MessageResponse ms = new MessageResponse();
        questionService.deleteQuestions(deleteQuestionsReq);
        return ResponseEntity.ok(ms);
    }
}
