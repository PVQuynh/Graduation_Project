package com.example.hust_learning_server.controller;

import com.example.hust_learning_server.dto.request.SetPrimaryForVocabularyImage;
import com.example.hust_learning_server.dto.request.UpdateVocabularyImageReq;
import com.example.hust_learning_server.dto.request.VocabularyImageReq;
import com.example.hust_learning_server.dto.response.MessageResponse;
import com.example.hust_learning_server.service.VocabularyImageService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/vocabulary-images")
public class VocabularyImageController {
    private final VocabularyImageService vocabularyImageService;

    @PostMapping
    public ResponseEntity<MessageResponse> addVocabularyImage(@RequestBody VocabularyImageReq vocabularyImageReq) {
        MessageResponse ms = new MessageResponse();
        vocabularyImageService.addVocabularyImage(vocabularyImageReq);
        return ResponseEntity.ok(ms);
    }

    @PostMapping("/add-list")
    public ResponseEntity<MessageResponse> addVocabularyImageList(@RequestBody List<VocabularyImageReq> vocabularyImageReqList) {
        MessageResponse ms = new MessageResponse();
        vocabularyImageService.addVocabularyImageList(vocabularyImageReqList);
        return ResponseEntity.ok(ms);
    }

    @PutMapping
    public ResponseEntity<MessageResponse> updateVocabularyImage(@RequestBody UpdateVocabularyImageReq updateVocabularyImageReq) {
        MessageResponse ms = new MessageResponse();
        vocabularyImageService.updateVocabularyImage(updateVocabularyImageReq);
        return ResponseEntity.ok(ms);
    }

    @PutMapping("/set-primary")
    public ResponseEntity<MessageResponse> setPrimaryForVocabularyImage(@RequestBody SetPrimaryForVocabularyImage setPrimaryForVocabularyImage) {
        MessageResponse ms = new MessageResponse();
        vocabularyImageService.setPrimaryForVocabularyImage(setPrimaryForVocabularyImage);
        return ResponseEntity.ok(ms);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<MessageResponse> deleteVocabularyImage(@PathVariable long id) {
        MessageResponse ms = new MessageResponse();
        vocabularyImageService.deleteById(id);
        return ResponseEntity.ok(ms);
    }

}
