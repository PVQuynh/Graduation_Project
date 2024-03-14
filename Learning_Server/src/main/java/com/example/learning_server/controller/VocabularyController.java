package com.example.learning_server.controller;

import com.example.learning_server.constant.ExceptionConstant;
import com.example.learning_server.constant.HTTPCode;
import com.example.learning_server.dto.PageDTO;
import com.example.learning_server.dto.request.SearchParamReq;
import com.example.learning_server.dto.request.UpdateVocabularyReq;
import com.example.learning_server.dto.request.VocabularyLimitReq;
import com.example.learning_server.dto.request.VocabularyReq;
import com.example.learning_server.dto.response.VocabularyRes;
import com.example.learning_server.dto.response.MessageResponse;
import com.example.learning_server.service.VocabularySerivce;
import lombok.RequiredArgsConstructor;
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
            ms.code = HTTPCode.RESOURCE_NOT_FOUND;
            ms.message = ExceptionConstant.RESOURCE_NOT_FOUND;
        }
        return ms;
    }

    @PostMapping("/limits-topic")
    public MessageResponse getAllVocabularies(@RequestBody VocabularyLimitReq vocabularyLimitReq) {

        MessageResponse ms = new MessageResponse();
        try {
            ms.data = vocabularyService.vocabularyLimits(vocabularyLimitReq);
        } catch (Exception ex) {
            ms.code = HTTPCode.RESOURCE_NOT_FOUND;
            ms.message = ExceptionConstant.RESOURCE_NOT_FOUND;
        }
        return ms;
    }

    @PostMapping("/search")
    public PageDTO<VocabularyRes> getLists(@RequestBody SearchParamReq searchParamReq){
        return  vocabularyService.search(searchParamReq);
    }

    @PostMapping
    public MessageResponse addVocabulary(@RequestBody VocabularyReq vocabularyReq) {
        MessageResponse ms = new MessageResponse();
        try {
            vocabularyService.addVocabulary(vocabularyReq);
        } catch (Exception ex) {
            ms.code = HTTPCode.INTERNAL_SERVER_ERROR;
            ms.message = ExceptionConstant.INTERNAL_SERVER_ERROR;
        }
        return ms;
    }

    @PutMapping
    public MessageResponse updateVocabulary(@RequestBody UpdateVocabularyReq updateVocabularyReq) {
        MessageResponse ms = new MessageResponse();
        try {
            vocabularyService.updateVocabulary(updateVocabularyReq);
        } catch (Exception ex) {
            ms.code = HTTPCode.INTERNAL_SERVER_ERROR;
            ms.message = ExceptionConstant.INTERNAL_SERVER_ERROR;
        }
        return ms;
    }

    @DeleteMapping("/{id}")
    public MessageResponse deleteVocabularyById(@PathVariable("id") long id) {

        MessageResponse ms = new MessageResponse();
        try {
            vocabularyService.deleteById(id);
        } catch (Exception ex) {
            ms.code = HTTPCode.INTERNAL_SERVER_ERROR;
            ms.message = ExceptionConstant.INTERNAL_SERVER_ERROR;
        }
        return ms;
    }


}
