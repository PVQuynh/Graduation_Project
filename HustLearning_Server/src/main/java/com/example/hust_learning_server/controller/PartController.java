package com.example.hust_learning_server.controller;

import com.example.hust_learning_server.dto.request.*;
import com.example.hust_learning_server.dto.response.MessageResponse;
import com.example.hust_learning_server.service.PartService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/parts")
public class PartController {

    private final PartService partService;

    @PostMapping
    public ResponseEntity<MessageResponse> addPart(@RequestBody PartReq partReq) {
        MessageResponse ms = new MessageResponse();
        partService.addPart(partReq);
        return ResponseEntity.ok(ms);
    }

    @PostMapping("/add-list")
    public ResponseEntity<MessageResponse> addPartList(@RequestBody List<PartReq> partReqList) {
        MessageResponse ms = new MessageResponse();
        partService.addParts(partReqList);
        return ResponseEntity.ok(ms);
    }

    @GetMapping
    public ResponseEntity<MessageResponse> getPart(
            @RequestParam long partId
    ) {
        MessageResponse ms = new MessageResponse();
        ms.data = partService.getPart(partId);
        return ResponseEntity.ok(ms);
    }

    @GetMapping("/all")
    public ResponseEntity<MessageResponse> getAllPart(
            @RequestParam(required = false, defaultValue = "0") long lessonId
    ) {
        MessageResponse ms = new MessageResponse();
        ms.data = partService.getAllParts(lessonId);
        return ResponseEntity.ok(ms);
    }

    @PutMapping
    public ResponseEntity<MessageResponse> updatePart(@RequestBody UpdatePartReq updatePartReq) {
        MessageResponse ms = new MessageResponse();
        partService.updatePart(updatePartReq);
        return ResponseEntity.ok(ms);
    }

    @DeleteMapping("/{partId}")
    public ResponseEntity<MessageResponse> deletePartById(@PathVariable("partId") long partId) {
        MessageResponse ms = new MessageResponse();
        partService.deletePart(partId);
        return ResponseEntity.ok(ms);
    }

}
