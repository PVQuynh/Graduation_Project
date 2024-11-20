package com.example.hust_learning_server.controller;

import com.example.hust_learning_server.dto.request.UpdatePartImageReq;
import com.example.hust_learning_server.dto.response.MessageResponse;
import com.example.hust_learning_server.service.PartImageService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/part-images")
public class PartImageController {
    private final PartImageService partImageService;

    @PutMapping
    public ResponseEntity<MessageResponse> updatePartImage(@RequestBody UpdatePartImageReq updatePartImageReq) {
        MessageResponse ms = new MessageResponse();
        partImageService.updatePartImage(updatePartImageReq);
        return ResponseEntity.ok(ms);
    }

}
