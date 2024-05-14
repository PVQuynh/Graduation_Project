package com.example.hust_learning_server.controller;

import com.example.hust_learning_server.dto.request.AnswerReq;
import com.example.hust_learning_server.dto.request.UpdateAnswerReq;
import com.example.hust_learning_server.dto.response.MessageResponse;
import com.example.hust_learning_server.service.AnswerService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/answers")
@RequiredArgsConstructor
public class AnswerController {

    @Autowired
    private AnswerService answerService;

    @PostMapping
    public MessageResponse addAnswer(@RequestBody AnswerReq answerReq) {
        MessageResponse ms = new MessageResponse();
        try {
            answerService.addAnswer(answerReq);
        } catch (Exception ex) {
            ms.code = HttpStatus.INTERNAL_SERVER_ERROR.value();
            ms.message = HttpStatus.INTERNAL_SERVER_ERROR.getReasonPhrase();
        }
        return ms;
    }

    @PutMapping
    public MessageResponse updateAnswer(@RequestBody UpdateAnswerReq updateAnswerReq) {
        MessageResponse ms = new MessageResponse();
        try {
            answerService.updateAnswer(updateAnswerReq);
        } catch (Exception ex) {
            ms.code = HttpStatus.INTERNAL_SERVER_ERROR.value();
            ms.message = HttpStatus.INTERNAL_SERVER_ERROR.getReasonPhrase();
        }
        return ms;
    }

    @DeleteMapping("/{id}")
    public MessageResponse deleteAnswer(@PathVariable("id") long id) {
        MessageResponse ms = new MessageResponse();
        try{
            answerService.deleteAnswer(id);
        } catch (Exception ex) {
            ms.code = HttpStatus.INTERNAL_SERVER_ERROR.value();
            ms.message = HttpStatus.INTERNAL_SERVER_ERROR.getReasonPhrase();
        }
        return ms;
    }


}
