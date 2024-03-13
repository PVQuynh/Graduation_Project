package com.example.HustLearning.controller;

import com.example.HustLearning.constant.HTTPCode;
import com.example.HustLearning.dto.PageDTO;
import com.example.HustLearning.dto.request.SearchParamReq;
import com.example.HustLearning.dto.request.UpdateVocabularyReq;
import com.example.HustLearning.dto.request.VocabularyLimitReq;
import com.example.HustLearning.dto.request.VocabularyReq;
import com.example.HustLearning.dto.response.VocabularyRes;
import com.example.HustLearning.dto.response.MessagesResponse;
import com.example.HustLearning.service.VocabularySerivce;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/vocabularies")
public class VocabularyController {

    private final VocabularySerivce vocabularyService;

    @GetMapping("/{topicId}")
    public List<VocabularyRes> getAllVocabularies(@PathVariable("topicId") long topicId) {
        try {
            return vocabularyService.getVocabulariesByTopicId(topicId);
        } catch (Exception ex) {
          return  null;
        }
    }

    @PostMapping("/limits-topic")
    public List<VocabularyRes> getAllVocabularies(@RequestBody VocabularyLimitReq vocabularyLimitReq) {
        try {
            return vocabularyService.vocabularyLimits(vocabularyLimitReq);
        } catch (Exception ex) {
            return  null;
        }
    }

    @PostMapping("/search")
    public PageDTO<VocabularyRes> getLists(@RequestBody SearchParamReq searchParamReq){
        return  vocabularyService.search(searchParamReq);
    }

    @PostMapping
    public MessagesResponse addVocabulary(@RequestBody VocabularyReq vocabularyReq) {
        MessagesResponse ms = new MessagesResponse();
        try {
            vocabularyService.addVocabulary(vocabularyReq);
        } catch (Exception ex) {
            ms.code = HTTPCode.INTERAL_SERVER_ERROR;
            ms.message = ex.getMessage();
        }
        return ms;
    }

    @PutMapping
    public MessagesResponse updateVocabulary(@RequestBody UpdateVocabularyReq updateVocabularyReq) {
        MessagesResponse ms = new MessagesResponse();
        try {
            vocabularyService.updateVocabulary(updateVocabularyReq);
        } catch (Exception ex) {
            ms.code = HTTPCode.INTERAL_SERVER_ERROR;
            ms.message = ex.getMessage();
        }
        return ms;
    }

    @DeleteMapping("/{id}")
    public MessagesResponse deleteVocabularyById(@PathVariable("id") long id) {

        MessagesResponse ms = new MessagesResponse();
        try {
            vocabularyService.deleteById(id);
        } catch (Exception ex) {
            ms.code = HTTPCode.INTERAL_SERVER_ERROR;
            ms.message = ex.getMessage();

        }
        return ms;
    }


}
