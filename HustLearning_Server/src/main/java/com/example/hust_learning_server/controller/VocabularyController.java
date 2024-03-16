package com.example.hust_learning_server.controller;

import com.example.hust_learning_server.dto.PageDTO;
import com.example.hust_learning_server.dto.request.SearchParamReq;
import com.example.hust_learning_server.dto.request.UpdateVocabularyReq;
import com.example.hust_learning_server.dto.request.VocabularyLimitReq;
import com.example.hust_learning_server.dto.request.VocabularyReq;
import com.example.hust_learning_server.dto.response.VocabularyRes;
import com.example.hust_learning_server.dto.response.MessageResponse;
import com.example.hust_learning_server.exception.AlreadyExistsException;
import com.example.hust_learning_server.exception.BusinessLogicException;
import com.example.hust_learning_server.service.VocabularySerivce;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/vocabularies")
public class VocabularyController {

    private final VocabularySerivce vocabularyService;


    @GetMapping("/{topicId}")
    public MessageResponse getAllVocabularies(@PathVariable("topicId") long topicId) {

        MessageResponse ms = new MessageResponse();
        try {
            ms.data = vocabularyService.getVocabulariesByTopicId(topicId);
        } catch (Exception ex) {
            ms.code = HttpStatus.NOT_FOUND.value();
            ms.message = HttpStatus.NOT_FOUND.getReasonPhrase();
        }
        return ms;
    }


    @PostMapping("/limits-topic")
    public MessageResponse getAllVocabularies(@RequestBody VocabularyLimitReq vocabularyLimitReq) {

        MessageResponse ms = new MessageResponse();
        try {
            ms.data = vocabularyService.vocabularyLimits(vocabularyLimitReq);
        } catch (Exception ex) {
            ms.code = HttpStatus.NOT_FOUND.value();
            ms.message = HttpStatus.NOT_FOUND.getReasonPhrase();
        }
        return ms;
    }

    @PostMapping("/search")
    public PageDTO<VocabularyRes> getLists(@RequestBody SearchParamReq searchParamReq) {
        return vocabularyService.search(searchParamReq);
    }

    @PostMapping
    public MessageResponse addVocabulary(@RequestBody VocabularyReq vocabularyReq) {
        MessageResponse ms = new MessageResponse();
        try {
            vocabularyService.addVocabulary(vocabularyReq);
        } catch (AlreadyExistsException ex) {
            ms.code = HttpStatus.CONFLICT.value();
            ms.message = HttpStatus.CONFLICT.getReasonPhrase(); // not fix
        } catch (BusinessLogicException ex) {
            ms.code = HttpStatus.INTERNAL_SERVER_ERROR.value();
            ms.message = HttpStatus.INTERNAL_SERVER_ERROR.getReasonPhrase(); // not fix
        }
        return ms;
    }

    @PutMapping
    public MessageResponse updateVocabulary(@RequestBody UpdateVocabularyReq updateVocabularyReq) {
        MessageResponse ms = new MessageResponse();
        try {
            vocabularyService.updateVocabulary(updateVocabularyReq);
        } catch (Exception ex) {
            ms.code = HttpStatus.INTERNAL_SERVER_ERROR.value();
            ms.message = HttpStatus.INTERNAL_SERVER_ERROR.getReasonPhrase();
        }
        return ms;
    }

    @DeleteMapping("/{id}")
    public MessageResponse deleteVocabularyById(@PathVariable("id") long id) {

        MessageResponse ms = new MessageResponse();
        try {
            vocabularyService.deleteById(id);
        } catch (Exception ex) {
            ms.code = HttpStatus.INTERNAL_SERVER_ERROR.value();
            ms.message = HttpStatus.INTERNAL_SERVER_ERROR.getReasonPhrase();
        }
        return ms;
    }


}
