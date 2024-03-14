package com.example.learning_server.controller;


import com.example.learning_server.constant.ExceptionConstant;
import com.example.learning_server.constant.HTTPCode;
import com.example.learning_server.dto.request.QuestionReq;
import com.example.learning_server.dto.request.QuestionLimitReq;
import com.example.learning_server.dto.request.UpdateQuestionReq;
import com.example.learning_server.dto.response.MessageResponse;
import com.example.learning_server.service.QuestionService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/questions")
public class QuestionController {

    private final QuestionService questionService;

    @GetMapping("/{topicId}")
    public MessageResponse getAllQuestion(@PathVariable("topicId") long topicId) {

        MessageResponse ms = new MessageResponse();
        try {
            ms.data = questionService.getQuestionsByTopicId(topicId);
        } catch (Exception ex) {
            ms.code = HTTPCode.RESOURCE_NOT_FOUND;
            ms.message = ExceptionConstant.RESOURCE_NOT_FOUND;
        }
        return ms;
    }

    @PostMapping("/limits-topic")
    public MessageResponse questionLimits(@RequestBody QuestionLimitReq questionLimitReq) {

        MessageResponse ms = new MessageResponse();
        try {
            ms.data = questionService.questionLimits(questionLimitReq);
        } catch (Exception ex) {
            ms.code = HTTPCode.RESOURCE_NOT_FOUND;
            ms.message = ExceptionConstant.RESOURCE_NOT_FOUND;
        }
        return ms;
    }

    @PostMapping
    public MessageResponse addQuestion(@RequestBody QuestionReq questionReq) {
        MessageResponse ms = new MessageResponse();
        try {
             questionService.addQuestion(questionReq);
        } catch (Exception ex) {
            ms.code = HTTPCode.INTERNAL_SERVER_ERROR;
            ms.message = ExceptionConstant.INTERNAL_SERVER_ERROR;
        }
        return ms;
    }

    @PutMapping
    public MessageResponse updateQuestion(@RequestBody UpdateQuestionReq updateQuestionReq) {
        MessageResponse ms = new MessageResponse();
        try {
            questionService.updateQuestion(updateQuestionReq);
        } catch (Exception ex) {
            ms.code = HTTPCode.INTERNAL_SERVER_ERROR;
            ms.message = ExceptionConstant.INTERNAL_SERVER_ERROR;
        }
        return ms;
    }

    @DeleteMapping("/{id}")
    public MessageResponse deleteQuestion(@PathVariable long id) {
        MessageResponse ms = new MessageResponse();
        try {
            questionService.deleteQuestionById(id);
        }
        catch (Exception ex) {
            ms.code = HTTPCode.INTERNAL_SERVER_ERROR;
            ms.message = ExceptionConstant.INTERNAL_SERVER_ERROR;
        }
        return  ms;
    }
}
