package com.example.learning_server.controller;

import com.example.learning_server.constant.HTTPCode;
import com.example.learning_server.dto.request.AnswerReq;
import com.example.learning_server.dto.request.UpdateAnswerReq;
import com.example.learning_server.dto.response.MessagesResponse;
import com.example.learning_server.service.AnswerService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/answers")
@RequiredArgsConstructor
public class AnswerController {
    private final AnswerService answerService;

    @PostMapping
    public MessagesResponse addAnwser(@RequestBody AnswerReq answerReq) {
        MessagesResponse ms = new MessagesResponse();
        try {
            answerService.addAnswer(answerReq);
        } catch (Exception ex) {
            ms.code = HTTPCode.INTERAL_SERVER_ERROR;
            ms.message = ex.getMessage();
        }
        return ms;
    }

    @PutMapping
    public MessagesResponse updateAnwser(@RequestBody UpdateAnswerReq updateAnswerReq) {
        MessagesResponse ms = new MessagesResponse();
        try {
            answerService.updateAnswer(updateAnswerReq);
        } catch (Exception ex) {
            ms.code = HTTPCode.INTERAL_SERVER_ERROR;
            ms.message = ex.getMessage();
        }
        return ms;
    }

    @DeleteMapping("/{id}")
    public MessagesResponse deleteAnswer(@PathVariable("id") long id) {
        MessagesResponse ms = new MessagesResponse();
        try {
            answerService.deleteAnswer(id);
        } catch (Exception ex) {
            ms.code = HTTPCode.INTERAL_SERVER_ERROR;
            ms.message = ex.getMessage();
        }
        return ms;
    }
}
