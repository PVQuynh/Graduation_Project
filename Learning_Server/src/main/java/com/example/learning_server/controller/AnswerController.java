package com.example.learning_server.controller;

import com.example.learning_server.constant.ExceptionConstant;
import com.example.learning_server.constant.HTTPCode;
import com.example.learning_server.dto.request.AnswerReq;
import com.example.learning_server.dto.request.UpdateAnswerReq;
import com.example.learning_server.dto.response.MessageResponse;
import com.example.learning_server.service.AnswerService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/answers")
@RequiredArgsConstructor
public class AnswerController {
    private final AnswerService answerService;

    @PostMapping
    public MessageResponse addAnswer(@RequestBody AnswerReq answerReq) {
        MessageResponse ms = new MessageResponse();
        try {
            answerService.addAnswer(answerReq);
        } catch (Exception ex) {
            ms.code = HTTPCode.INTERNAL_SERVER_ERROR;
            ms.message = ExceptionConstant.INTERNAL_SERVER_ERROR;
        }
        return ms;
    }

    @PutMapping
    public MessageResponse updateAnswer(@RequestBody UpdateAnswerReq updateAnswerReq) {
        MessageResponse ms = new MessageResponse();
        try {
            answerService.updateAnswer(updateAnswerReq);
        } catch (Exception ex) {
            ms.code = HTTPCode.INTERNAL_SERVER_ERROR;
            ms.message = ExceptionConstant.INTERNAL_SERVER_ERROR;
        }
        return ms;
    }

    @DeleteMapping("/{id}")
    public MessageResponse deleteAnswer(@PathVariable("id") long id) {
        MessageResponse ms = new MessageResponse();
        try {
            answerService.deleteAnswer(id);
        } catch (Exception ex) {
            ms.code = HTTPCode.INTERNAL_SERVER_ERROR;
            ms.message = ExceptionConstant.INTERNAL_SERVER_ERROR;
        }
        return ms;
    }
}
