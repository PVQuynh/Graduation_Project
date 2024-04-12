package com.example.hust_learning_server.controller;

import com.example.hust_learning_server.dto.request.SetPrimaryForVocabularyMedium;
import com.example.hust_learning_server.dto.request.UpdateVocabularyMediumReq;
import com.example.hust_learning_server.dto.request.VocabularyMediumReq;
import com.example.hust_learning_server.dto.request.VocabularyReq;
import com.example.hust_learning_server.dto.response.MessageResponse;
import com.example.hust_learning_server.exception.BusinessLogicException;
import com.example.hust_learning_server.service.VocabularyMediumService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/vocabulary-media")
public class VocabularyMediumController {
    private final VocabularyMediumService vocabularyMediumService;

    @PostMapping
    public MessageResponse addVocabularyMedium(@RequestBody VocabularyMediumReq vocabularyMediumReq) {
        MessageResponse ms = new MessageResponse();
        try {
            vocabularyMediumService.addVocabularyMedium(vocabularyMediumReq);
        } catch (BusinessLogicException ex) {
            ms.code = HttpStatus.INTERNAL_SERVER_ERROR.value();
            ms.message = HttpStatus.INTERNAL_SERVER_ERROR.getReasonPhrase();
        }
        return ms;
    }

    @PostMapping("/add-list")
    public MessageResponse addVocabularyMediumList(@RequestBody List<VocabularyMediumReq> vocabularyMediumReqList) {
        MessageResponse ms = new MessageResponse();
        try {
            vocabularyMediumService.addVocabularyMediumList(vocabularyMediumReqList);
        } catch (BusinessLogicException ex) {
            ms.code = HttpStatus.INTERNAL_SERVER_ERROR.value();
            ms.message = HttpStatus.INTERNAL_SERVER_ERROR.getReasonPhrase();
        }
        return ms;
    }

    @PutMapping
    public MessageResponse updateVocabularyMedium(@RequestBody UpdateVocabularyMediumReq updateVocabularyMediumReq) {
        MessageResponse ms = new MessageResponse();
        try {
            vocabularyMediumService.updateVocabularyMedium(updateVocabularyMediumReq);
        } catch (BusinessLogicException ex) {
            ms.code = HttpStatus.INTERNAL_SERVER_ERROR.value();
            ms.message = HttpStatus.INTERNAL_SERVER_ERROR.getReasonPhrase();
        }
        return ms;
    }

    @PutMapping("/set-primary")
    public MessageResponse setPrimaryForVocabularyMedium(@RequestBody SetPrimaryForVocabularyMedium setPrimaryForVocabularyMedium) {
        MessageResponse ms = new MessageResponse();
        try {
            vocabularyMediumService.setPrimaryForVocabularyMedium(setPrimaryForVocabularyMedium);
        } catch (BusinessLogicException ex) {
            ms.code = HttpStatus.INTERNAL_SERVER_ERROR.value();
            ms.message = HttpStatus.INTERNAL_SERVER_ERROR.getReasonPhrase();
        }
        return ms;
    }

    @DeleteMapping("/{id}")
    public MessageResponse deleteVocabularyMedium(@PathVariable long id) {
        MessageResponse ms = new MessageResponse();
        try {
            vocabularyMediumService.deleteById(id);
        } catch (BusinessLogicException ex) {
            ms.code = HttpStatus.INTERNAL_SERVER_ERROR.value();
            ms.message = HttpStatus.INTERNAL_SERVER_ERROR.getReasonPhrase();
        }
        return ms;
    }

}
