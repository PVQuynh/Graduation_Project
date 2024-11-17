package com.example.hust_learning_server.controller;

import com.example.hust_learning_server.dto.request.PartReq;
import com.example.hust_learning_server.dto.response.PartRes;
import com.example.hust_learning_server.dto.response.MessageResponse;
import com.example.hust_learning_server.service.PartService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("parts")
public class PartController {
  private final PartService partService;

    @PostMapping
    public ResponseEntity<MessageResponse> addPart(@RequestBody PartReq partReq) {
        MessageResponse ms = new MessageResponse();
        partService.add(partReq);
        return ResponseEntity.ok(ms);

    }

    @GetMapping("/{partId}")
    public ResponseEntity<MessageResponse> getPartById(
            @PathVariable("partId") Long partId
    ) {
        MessageResponse ms = new MessageResponse();
        ms.data = partService.getById(partId);
        return ResponseEntity.ok(ms);

    }

    @GetMapping("/all")
    public ResponseEntity<MessageResponse> getAllParts(
            @RequestParam(required = false) Long lessonId
    ) {
        MessageResponse ms = new MessageResponse();
        ms.data = partService.getAll(lessonId);
        return ResponseEntity.ok(ms);

    }

    @PutMapping
    public ResponseEntity<MessageResponse> updatePart(@RequestBody PartRes partRes) {
        MessageResponse ms = new MessageResponse();
        partService.update(partRes);
        return ResponseEntity.ok(ms);
    }

    @DeleteMapping("/{partId}")
    public ResponseEntity<MessageResponse> deletePartById(@PathVariable("partId") Long partId) {
        MessageResponse ms = new MessageResponse();
        partService.deleteById(partId);
        return ResponseEntity.ok(ms);
    }
}
