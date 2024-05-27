package com.example.hust_learning_server.controller;

import com.example.hust_learning_server.dto.request.AnswerReq;
import com.example.hust_learning_server.dto.request.UpdateAnswerReq;
import com.example.hust_learning_server.dto.response.MessageResponse;
import com.example.hust_learning_server.service.AnswerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/answers")
public class AnswerController {

    @Autowired
    private AnswerService answerService;

    @PostMapping
    public ResponseEntity<MessageResponse> addAnswer(@RequestBody AnswerReq answerReq) {
        MessageResponse ms = new MessageResponse();
        answerService.addAnswer(answerReq);
        return ResponseEntity.ok(ms);
    }

    @PutMapping
    public ResponseEntity<MessageResponse> updateAnswer(@RequestBody UpdateAnswerReq updateAnswerReq) {
        MessageResponse ms = new MessageResponse();
        answerService.updateAnswer(updateAnswerReq);
        return ResponseEntity.ok(ms);

    }

    @DeleteMapping("/{id}")
    public ResponseEntity<MessageResponse> deleteAnswer(@PathVariable("id") long id) {
        MessageResponse ms = new MessageResponse();
        answerService.deleteAnswer(id);
        return ResponseEntity.ok(ms);
    }


}
