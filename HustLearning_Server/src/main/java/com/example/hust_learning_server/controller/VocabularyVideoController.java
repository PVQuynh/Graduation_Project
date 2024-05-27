package com.example.hust_learning_server.controller;

import com.example.hust_learning_server.dto.request.SetPrimaryForVocabularyVideo;
import com.example.hust_learning_server.dto.request.UpdateVocabularyVideoReq;
import com.example.hust_learning_server.dto.request.VocabularyVideoReq;
import com.example.hust_learning_server.dto.response.MessageResponse;
import com.example.hust_learning_server.service.VocabularyVideoService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/vocabulary-videos")
public class VocabularyVideoController {
    private final VocabularyVideoService vocabularyVideoService;

    @PostMapping
    public ResponseEntity<MessageResponse> addVocabularyVideo(@RequestBody VocabularyVideoReq vocabularyVideoReq) {
        MessageResponse ms = new MessageResponse();
        vocabularyVideoService.addVocabularyVideo(vocabularyVideoReq);
        return ResponseEntity.ok(ms);
    }

    @PostMapping("/add-list")
    public ResponseEntity<MessageResponse> addVocabularyVideoList(@RequestBody List<VocabularyVideoReq> vocabularyVideoReqList) {
        MessageResponse ms = new MessageResponse();
        vocabularyVideoService.addVocabularyVideoList(vocabularyVideoReqList);
        return ResponseEntity.ok(ms);
    }

    @PutMapping
    public ResponseEntity<MessageResponse> updateVocabularyVideo(@RequestBody UpdateVocabularyVideoReq updateVocabularyVideoReq) {
        MessageResponse ms = new MessageResponse();
        vocabularyVideoService.updateVocabularyVideo(updateVocabularyVideoReq);
        return ResponseEntity.ok(ms);
    }

    @PutMapping("/set-primary")
    public ResponseEntity<MessageResponse> setPrimaryForVocabularyVideo(@RequestBody SetPrimaryForVocabularyVideo setPrimaryForVocabularyVideo) {
        MessageResponse ms = new MessageResponse();
        vocabularyVideoService.setPrimaryForVocabularyVideo(setPrimaryForVocabularyVideo);
        return ResponseEntity.ok(ms);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<MessageResponse> deleteVocabularyVideo(@PathVariable long id) {
        MessageResponse ms = new MessageResponse();
        vocabularyVideoService.deleteById(id);
        return ResponseEntity.ok(ms);
    }

}
