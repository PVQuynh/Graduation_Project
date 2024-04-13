package com.example.hust_learning_server.controller;

import com.example.hust_learning_server.dto.request.SetPrimaryForVocabularyImage;
import com.example.hust_learning_server.dto.request.UpdateVocabularyImageReq;
import com.example.hust_learning_server.dto.request.VocabularyImageReq;
import com.example.hust_learning_server.dto.response.MessageResponse;
import com.example.hust_learning_server.exception.AlreadyExistsException;
import com.example.hust_learning_server.exception.BusinessLogicException;
import com.example.hust_learning_server.service.VocabularyImageService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/vocabulary-images")
public class VocabularyImageController {
    private final VocabularyImageService vocabularyImageService;

    @PostMapping
    public MessageResponse addVocabularyImage(@RequestBody VocabularyImageReq vocabularyImageReq) {
        MessageResponse ms = new MessageResponse();
        try {
            vocabularyImageService.addVocabularyImage(vocabularyImageReq);
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
    public MessageResponse addVocabularyImageList(@RequestBody List<VocabularyImageReq> vocabularyImageReqList) {
        MessageResponse ms = new MessageResponse();
        try {
            vocabularyImageService.addVocabularyImageList(vocabularyImageReqList);
        } catch (BusinessLogicException ex) {
            ms.code = HttpStatus.INTERNAL_SERVER_ERROR.value();
            ms.message = HttpStatus.INTERNAL_SERVER_ERROR.getReasonPhrase();
        }
        return ms;
    }

    @PutMapping
    public MessageResponse updateVocabularyImage(@RequestBody UpdateVocabularyImageReq updateVocabularyImageReq) {
        MessageResponse ms = new MessageResponse();
        try {
            vocabularyImageService.updateVocabularyImage(updateVocabularyImageReq);
        } catch (BusinessLogicException ex) {
            ms.code = HttpStatus.INTERNAL_SERVER_ERROR.value();
            ms.message = HttpStatus.INTERNAL_SERVER_ERROR.getReasonPhrase();
        }
        return ms;
    }

    @PutMapping("/set-primary")
    public MessageResponse setPrimaryForVocabularyImage(@RequestBody SetPrimaryForVocabularyImage setPrimaryForVocabularyImage) {
        MessageResponse ms = new MessageResponse();
        try {
            vocabularyImageService.setPrimaryForVocabularyImage(setPrimaryForVocabularyImage);
        } catch (BusinessLogicException ex) {
            ms.code = HttpStatus.INTERNAL_SERVER_ERROR.value();
            ms.message = HttpStatus.INTERNAL_SERVER_ERROR.getReasonPhrase();
        }
        return ms;
    }

    @DeleteMapping("/{id}")
    public MessageResponse deleteVocabularyImage(@PathVariable long id) {
        MessageResponse ms = new MessageResponse();
        try {
            vocabularyImageService.deleteById(id);
        } catch (BusinessLogicException ex) {
            ms.code = HttpStatus.INTERNAL_SERVER_ERROR.value();
            ms.message = HttpStatus.INTERNAL_SERVER_ERROR.getReasonPhrase();
        }
        return ms;
    }

}
