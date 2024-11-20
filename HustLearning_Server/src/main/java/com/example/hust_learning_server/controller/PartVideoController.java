package com.example.hust_learning_server.controller;

import com.example.hust_learning_server.dto.request.UpdatePartVideoReq;
import com.example.hust_learning_server.dto.response.MessageResponse;
import com.example.hust_learning_server.service.PartVideoService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/part-videos")
public class PartVideoController {
    private final PartVideoService partVideoService;

    @PutMapping
    public ResponseEntity<MessageResponse> updatePartVideo(@RequestBody UpdatePartVideoReq updatePartVideoReq) {
        MessageResponse ms = new MessageResponse();
        partVideoService.updatePartVideo(updatePartVideoReq);
        return ResponseEntity.ok(ms);
    }

}
