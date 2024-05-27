package com.example.hust_learning_server.controller;


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

    @GetMapping("/{topicId}")
    public ResponseEntity<MessageResponse> getAllQuestion(@PathVariable("topicId") long topicId) {
        MessageResponse ms = new MessageResponse();
        ms.data = questionService.getQuestionsByTopicId(topicId);
        return ResponseEntity.ok(ms);

    }

    @PostMapping("/limits-topic")
    public ResponseEntity<MessageResponse> questionLimits(@RequestBody QuestionLimitReq questionLimitReq) {
        MessageResponse ms = new MessageResponse();
        ms.data = questionService.questionLimits(questionLimitReq);
        return ResponseEntity.ok(ms);

    }

    @GetMapping("/limits-topic/v2")
    public ResponseEntity<MessageResponse> questionLimits_v2(
            @RequestParam(defaultValue = "1", required = true) int page,
            @RequestParam(defaultValue = "10", required = true) int size,
            @RequestParam(required = true) long topicId
    ) {
        MessageResponse ms = new MessageResponse();
        ms.data = questionService.questionLimits_v2(page, size, topicId);
        return ResponseEntity.ok(ms);

    }

    @PostMapping
    public ResponseEntity<MessageResponse> addQuestion(@RequestBody QuestionReq questionReq) {
        MessageResponse ms = new MessageResponse();
        questionService.addQuestion(questionReq);
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
}
