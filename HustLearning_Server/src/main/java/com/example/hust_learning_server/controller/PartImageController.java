package com.example.hust_learning_server.controller;

import com.example.hust_learning_server.dto.request.PartImageReq;
import com.example.hust_learning_server.dto.request.UpdatePartImageReq;
import com.example.hust_learning_server.dto.response.MessageResponse;
import com.example.hust_learning_server.service.PartImageService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/part-images")
public class PartImageController {
    private final PartImageService partImageService;

    @PostMapping
    public ResponseEntity<MessageResponse> addPartImage(@RequestBody PartImageReq partImageReq) {
        MessageResponse ms = new MessageResponse();
        partImageService.addPartImage(partImageReq);
        return ResponseEntity.ok(ms);
    }

    @PostMapping("/add-list")
    public ResponseEntity<MessageResponse> addPartImages(@RequestBody List<PartImageReq> partImageReqs) {
        MessageResponse ms = new MessageResponse();
        partImageService.addPartImages(partImageReqs);
        return ResponseEntity.ok(ms);
    }

    @GetMapping
    public ResponseEntity<MessageResponse> getPartImage(
            @RequestParam long partImageId
    ) {
        MessageResponse ms = new MessageResponse();
        ms.data = partImageService.getPartImage(partImageId);
        return ResponseEntity.ok(ms);
    }

    @PutMapping
    public ResponseEntity<MessageResponse> updatePartImage(@RequestBody UpdatePartImageReq updatePartImageReq) {
        MessageResponse ms = new MessageResponse();
        partImageService.updatePartImage(updatePartImageReq);
        return ResponseEntity.ok(ms);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<MessageResponse> deletePartImage(@PathVariable long id) {
        MessageResponse ms = new MessageResponse();
        partImageService.deleteById(id);
        return ResponseEntity.ok(ms);
    }

}
