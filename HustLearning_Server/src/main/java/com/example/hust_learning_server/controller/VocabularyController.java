package com.example.hust_learning_server.controller;

import com.example.hust_learning_server.dto.PageDTO;
import com.example.hust_learning_server.dto.request.*;
import com.example.hust_learning_server.dto.response.VocabularyRes;
import com.example.hust_learning_server.dto.response.MessageResponse;
import com.example.hust_learning_server.exception.AlreadyExistsException;
import com.example.hust_learning_server.exception.BusinessLogicException;
import com.example.hust_learning_server.service.VocabularySerivce;
import lombok.RequiredArgsConstructor;
import org.springframework.data.repository.query.Param;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/vocabularies")
public class VocabularyController {

    private final VocabularySerivce vocabularyService;

    @GetMapping("/all")
    public MessageResponse getAllVocabulary(@RequestParam(required = false) Long topicId, @RequestParam(required = false) String content) {

        MessageResponse ms = new MessageResponse();
        try {
            if (topicId != null && content != null) {
                ms.data = vocabularyService.getVocabularyByTopicIdAndContent(topicId, content);
            }
            else if (topicId != null && content == null) {
                ms.data = vocabularyService.getVocabulariesByTopicId(topicId);
            }
            else if (topicId == null && content != null) {
                ms.data = vocabularyService.getVocabulariesByContent(content);
            } else {
                ms.data = vocabularyService.getAllVocabulary();
            }
        } catch (Exception ex) {
            ms.code = HttpStatus.NOT_FOUND.value();
            ms.message = HttpStatus.NOT_FOUND.getReasonPhrase();
        }
        return ms;
    }


    @GetMapping("/{topicId}")
    public MessageResponse getAllVocabulariesByTopicId(@PathVariable("topicId") long topicId) {

        MessageResponse ms = new MessageResponse();
        try {
            ms.data = vocabularyService.getVocabulariesByTopicId(topicId);
        } catch (Exception ex) {
            ms.code = HttpStatus.NOT_FOUND.value();
            ms.message = HttpStatus.NOT_FOUND.getReasonPhrase();
        }
        return ms;
    }

    @PostMapping ("/get-by-content")
    public MessageResponse getExactVocabularies(@RequestBody ExactVocabularyReq exactVocabularyReq) {

        MessageResponse ms = new MessageResponse();
        try {
            ms.data = vocabularyService.getExactVocabularies(exactVocabularyReq);
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
    public PageDTO<VocabularyRes> getLists(@RequestBody SearchVocabularyParamReq searchVocabularyParamReq) {
        return vocabularyService.search(searchVocabularyParamReq);
    }

    @PostMapping
    public MessageResponse addVocabulary(@RequestBody VocabularyReq vocabularyReq) {
        MessageResponse ms = new MessageResponse();
        try {
            vocabularyService.addVocabulary(vocabularyReq);
        } catch (BusinessLogicException ex) {
            ms.code = HttpStatus.INTERNAL_SERVER_ERROR.value();
            ms.message = HttpStatus.INTERNAL_SERVER_ERROR.getReasonPhrase(); // not fix
        }
        return ms;
    }

    @PostMapping("/add-list")
    public MessageResponse addVocabularyList(@RequestBody List<VocabularyReq> vocabularyReqList) {
        MessageResponse ms = new MessageResponse();
        try {
            vocabularyService.addVocabularyList(vocabularyReqList);
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
