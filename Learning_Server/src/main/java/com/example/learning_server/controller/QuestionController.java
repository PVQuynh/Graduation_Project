package com.example.learning_server.controller;


import com.example.learning_server.constant.HTTPCode;
import com.example.learning_server.dto.request.QuestionReq;
import com.example.learning_server.dto.request.QuestionLimitReq;
import com.example.learning_server.dto.request.UpdateQuestionReq;
import com.example.learning_server.dto.response.MessagesResponse;
import com.example.learning_server.dto.response.QuestionRes;
import com.example.learning_server.service.QuestionService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/questions")
public class QuestionController {

    private final QuestionService questionService;

    @GetMapping("/{topicId}")
    public List<QuestionRes> getAllQuestion(@PathVariable("topicId") long topicId) {
        try {
            return questionService.getQuestionsByTopicId(topicId);
        } catch (Exception ex) {
           return null;
        }
    }

    @PostMapping("/limits-topic")
    public List<QuestionRes> questionLimits(@RequestBody QuestionLimitReq questionLimitReq) {
        try {
            return questionService.questionLimits(questionLimitReq);
        } catch (Exception ex) {
           return null;
        }
    }

    @PostMapping
    public MessagesResponse addQuestion(@RequestBody QuestionReq questionReq) {
        MessagesResponse ms = new MessagesResponse();
        try {
             questionService.addQuestion(questionReq);
        } catch (Exception ex) {
            ms.code = HTTPCode.INTERAL_SERVER_ERROR;
            ms.message = ex.getMessage();
        }
        return ms;
    }

    @PutMapping
    public MessagesResponse updateQuestion(@RequestBody UpdateQuestionReq updateQuestionReq) {
        MessagesResponse ms = new MessagesResponse();
        try {
            questionService.updateQuestion(updateQuestionReq);
        } catch (Exception ex) {
            ms.code = HTTPCode.INTERAL_SERVER_ERROR;
            ms.message = ex.getMessage();
        }
        return ms;
    }

    @DeleteMapping("/{id}")
    public MessagesResponse deleteQuestion(@PathVariable long id) {
        MessagesResponse ms = new MessagesResponse();
        try {
            questionService.deleteQuestionById(id);
        }
        catch (Exception ex) {
            ms.code = HTTPCode.INTERAL_SERVER_ERROR;
            ms.message = ex.getMessage();
        }
        return  ms;
    }
}
