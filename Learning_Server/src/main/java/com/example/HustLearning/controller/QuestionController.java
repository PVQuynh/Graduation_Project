package com.example.HustLearning.controller;


import com.example.HustLearning.constant.HTTPCode;
import com.example.HustLearning.dto.request.QuestionReq;
import com.example.HustLearning.dto.request.QuestionSearchParam;
import com.example.HustLearning.dto.response.MessagesResponse;
import com.example.HustLearning.dto.response.QuestionRes;
import com.example.HustLearning.entity.Question;
import com.example.HustLearning.mapper.Impl.QuestionMapperImpl;
import com.example.HustLearning.service.QuestionService;
import com.example.HustLearning.service.TopicService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/questions")
public class QuestionController {

    private final QuestionService questionService;

//    @GetMapping("/{topicId}")
//    public MessagesResponse getAllQuestion(@PathVariable("topicId") long topicId) {
//        MessagesResponse ms = new MessagesResponse();
//        try {
//            ms.data = questionService.getQuestionsByTopicId(topicId);
//        } catch (Exception ex) {
//            ms.code = 404;
//            ms.message = ex.getMessage();
//        }
//
//
//        return ms;
//    }

    @PostMapping
    public MessagesResponse addQuestion(@RequestBody @Valid QuestionReq questionReq) {
        MessagesResponse ms = new MessagesResponse();
        try {
             questionService.addQuestion(questionReq);
        } catch (Exception ex) {
            ms.code = HTTPCode.INTERAL_SERVER_ERROR;
            ms.message = ex.getMessage();
        }
        return ms;
    }
    @PostMapping("/get-by-topic")
    public MessagesResponse getByTopic(@RequestBody @Valid QuestionSearchParam searchParam) {
        MessagesResponse ms = new MessagesResponse();
        try {
            ms.data=questionService.searchQuestion(searchParam);
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
