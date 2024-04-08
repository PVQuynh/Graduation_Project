package com.example.hust_learning_server.controller;


import com.example.hust_learning_server.dto.request.QuestionReq;
import com.example.hust_learning_server.dto.request.QuestionLimitReq;
import com.example.hust_learning_server.dto.request.UpdateQuestionReq;
import com.example.hust_learning_server.dto.response.MessageResponse;
import com.example.hust_learning_server.service.QuestionService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
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
            ms.code = HttpStatus.NOT_FOUND.value();
            ms.message = HttpStatus.NOT_FOUND.getReasonPhrase();
        }
        return ms;
    }

    @PostMapping("/limits-topic")
    public MessageResponse questionLimits(@RequestBody QuestionLimitReq questionLimitReq) {

        MessageResponse ms = new MessageResponse();
        try {
            ms.data = questionService.questionLimits(questionLimitReq);
        } catch (Exception ex) {
            ms.code = HttpStatus.NOT_FOUND.value();
            ms.message = HttpStatus.NOT_FOUND.getReasonPhrase();
        }
        return ms;
    }

    @GetMapping("/limits-topic/v2")
    public MessageResponse questionLimits_v2(
            @RequestParam(defaultValue = "1", required = true) int page,
            @RequestParam(defaultValue = "10", required = true) int size,
            @RequestParam(required = true) long topicId
    ) {
        MessageResponse ms = new MessageResponse();
        try {
            ms.data = questionService.questionLimits_v2(page, size, topicId);
        } catch (Exception ex) {
            ms.code = HttpStatus.NOT_FOUND.value();
            ms.message = HttpStatus.NOT_FOUND.getReasonPhrase();
        }
        return ms;
    }

    @PostMapping
    public MessageResponse addQuestion(@RequestBody QuestionReq questionReq) {
        MessageResponse ms = new MessageResponse();
        try {
             questionService.addQuestion(questionReq);
        } catch (Exception ex) {
            ms.code = HttpStatus.INTERNAL_SERVER_ERROR.value();
            ms.message = HttpStatus.INTERNAL_SERVER_ERROR.getReasonPhrase();
        }
        return ms;
    }

    @PutMapping
    public MessageResponse updateQuestion(@RequestBody UpdateQuestionReq updateQuestionReq) {
        MessageResponse ms = new MessageResponse();
        try {
            questionService.updateQuestion(updateQuestionReq);
        } catch (Exception ex) {
            ms.code = HttpStatus.INTERNAL_SERVER_ERROR.value();
            ms.message = HttpStatus.INTERNAL_SERVER_ERROR.getReasonPhrase();
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
            ms.code = HttpStatus.INTERNAL_SERVER_ERROR.value();
            ms.message = HttpStatus.INTERNAL_SERVER_ERROR.getReasonPhrase();
        }
        return  ms;
    }
}
