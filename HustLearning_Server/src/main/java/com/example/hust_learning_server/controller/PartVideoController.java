package com.example.hust_learning_server.controller;

import com.example.hust_learning_server.dto.request.PartVideoReq;
import com.example.hust_learning_server.dto.request.UpdatePartVideoReq;
import com.example.hust_learning_server.dto.response.MessageResponse;
import com.example.hust_learning_server.service.PartVideoService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/part-videos")
public class PartVideoController {
    private final PartVideoService partVideoService;

    @PostMapping
    public ResponseEntity<MessageResponse> addPartVideo(@RequestBody PartVideoReq partVideoReq) {
        MessageResponse ms = new MessageResponse();
        partVideoService.addPartVideo(partVideoReq);
        return ResponseEntity.ok(ms);
    }
    
    @PutMapping
    public ResponseEntity<MessageResponse> updatePartVideo(@RequestBody UpdatePartVideoReq updatePartVideoReq) {
        MessageResponse ms = new MessageResponse();
        partVideoService.updatePartVideo(updatePartVideoReq);
        return ResponseEntity.ok(ms);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<MessageResponse> deletePartVideo(@PathVariable long id) {
        MessageResponse ms = new MessageResponse();
        partVideoService.deleteById(id);
        return ResponseEntity.ok(ms);
    }

}
