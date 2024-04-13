package com.example.hust_learning_server.controller;

import com.example.hust_learning_server.dto.request.SetPrimaryForVocabularyVideo;
import com.example.hust_learning_server.dto.request.UpdateVocabularyVideoReq;
import com.example.hust_learning_server.dto.request.VocabularyVideoReq;
import com.example.hust_learning_server.dto.response.MessageResponse;
import com.example.hust_learning_server.exception.AlreadyExistsException;
import com.example.hust_learning_server.exception.BusinessLogicException;
import com.example.hust_learning_server.service.VocabularyVideoService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/vocabulary-videos")
public class VocabularyVideoController {
    private final VocabularyVideoService vocabularyVideoService;

    @PostMapping
    public MessageResponse addVocabularyVideo(@RequestBody VocabularyVideoReq vocabularyVideoReq) {
        MessageResponse ms = new MessageResponse();
        try {
            vocabularyVideoService.addVocabularyVideo(vocabularyVideoReq);
        } catch (AlreadyExistsException ex) {
            ms.code = HttpStatus.CONFLICT.value();
            ms.message = HttpStatus.CONFLICT.getReasonPhrase(); // not fix
        } catch (BusinessLogicException ex) {
            ms.code = HttpStatus.INTERNAL_SERVER_ERROR.value();
            ms.message = HttpStatus.INTERNAL_SERVER_ERROR.getReasonPhrase();
        }
        return ms;
    }

    @PostMapping("/add-list")
    public MessageResponse addVocabularyVideoList(@RequestBody List<VocabularyVideoReq> vocabularyVideoReqList) {
        MessageResponse ms = new MessageResponse();
        try {
            vocabularyVideoService.addVocabularyVideoList(vocabularyVideoReqList);
        } catch (BusinessLogicException ex) {
            ms.code = HttpStatus.INTERNAL_SERVER_ERROR.value();
            ms.message = HttpStatus.INTERNAL_SERVER_ERROR.getReasonPhrase();
        }
        return ms;
    }

    @PutMapping
    public MessageResponse updateVocabularyVideo(@RequestBody UpdateVocabularyVideoReq updateVocabularyVideoReq) {
        MessageResponse ms = new MessageResponse();
        try {
            vocabularyVideoService.updateVocabularyVideo(updateVocabularyVideoReq);
        } catch (BusinessLogicException ex) {
            ms.code = HttpStatus.INTERNAL_SERVER_ERROR.value();
            ms.message = HttpStatus.INTERNAL_SERVER_ERROR.getReasonPhrase();
        }
        return ms;
    }

    @PutMapping("/set-primary")
    public MessageResponse setPrimaryForVocabularyVideo(@RequestBody SetPrimaryForVocabularyVideo setPrimaryForVocabularyVideo) {
        MessageResponse ms = new MessageResponse();
        try {
            vocabularyVideoService.setPrimaryForVocabularyVideo(setPrimaryForVocabularyVideo);
        } catch (BusinessLogicException ex) {
            ms.code = HttpStatus.INTERNAL_SERVER_ERROR.value();
            ms.message = HttpStatus.INTERNAL_SERVER_ERROR.getReasonPhrase();
        }
        return ms;
    }

    @DeleteMapping("/{id}")
    public MessageResponse deleteVocabularyVideo(@PathVariable long id) {
        MessageResponse ms = new MessageResponse();
        try {
            vocabularyVideoService.deleteById(id);
        } catch (BusinessLogicException ex) {
            ms.code = HttpStatus.INTERNAL_SERVER_ERROR.value();
            ms.message = HttpStatus.INTERNAL_SERVER_ERROR.getReasonPhrase();
        }
        return ms;
    }

}
