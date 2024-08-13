package com.example.hust_learning_server.controller;

import com.example.hust_learning_server.dto.request.IntroductionReq;
import com.example.hust_learning_server.dto.response.MessageResponse;
import com.example.hust_learning_server.service.IntroductionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/introductions")
public class IntroductionController {

    @Autowired
    private IntroductionService introductionService;;

    @PostMapping
    public ResponseEntity<MessageResponse> addIntroduction(@RequestBody IntroductionReq introductionReq) {
        MessageResponse ms = new MessageResponse();
        introductionService.addIntroduction(introductionReq);
        return ResponseEntity.ok(ms);
    }

    @GetMapping
    public ResponseEntity<MessageResponse> getAllIntroduction() {
        MessageResponse ms = new MessageResponse();
        ms.data = introductionService.getIntroduction(1);
        return ResponseEntity.ok(ms);
    }

    @PutMapping
    public ResponseEntity<MessageResponse> updateIntroduction(@RequestBody IntroductionReq introductionReq) {
        MessageResponse ms = new MessageResponse();
        introductionService.updateIntroduction(1, introductionReq);
        return ResponseEntity.ok(ms);

    }
}
