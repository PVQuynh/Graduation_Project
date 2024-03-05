package com.example.HustLearning.controller;

import com.example.HustLearning.constant.ExceptionConstant;
import com.example.HustLearning.constant.HTTPCode;
import com.example.HustLearning.dto.PageDTO;
import com.example.HustLearning.dto.request.SearchParamReq;
import com.example.HustLearning.dto.request.VocabReq;
import com.example.HustLearning.dto.response.TopicRes;
import com.example.HustLearning.dto.response.VocabRes;
import com.example.HustLearning.dto.response.MessagesResponse;
import com.example.HustLearning.entity.Vocabulary;
import com.example.HustLearning.mapper.Impl.VocabularyMapperImpl;
import com.example.HustLearning.service.TopicService;
import com.example.HustLearning.service.VocabularySerivce;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/vocabularies")
public class VocabularyController {

    private final VocabularySerivce vocabularyService;


    @GetMapping("/{topicId}")
    public MessagesResponse getAllVocabularies(@PathVariable("topicId") long topicId) {
        MessagesResponse ms = new MessagesResponse();
        try {
            ms.data = vocabularyService.getVocabulariesByTopicId(topicId);

        } catch (Exception ex) {
            ms.code = HTTPCode.RESOURCE_NOT_FOUND;
            ms.message = ex.getMessage();
        }

        return ms;
    }

    @PostMapping
    public MessagesResponse addVocabulary(@RequestBody @Valid VocabReq vocabularyDTO) {
        MessagesResponse ms = new MessagesResponse();
        try {
            vocabularyService.addVocabulary(vocabularyDTO);
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

    @PostMapping("/api/search")
    public PageDTO<VocabRes> GetLists(@RequestBody SearchParamReq searchParamReq){
        return  vocabularyService.search(searchParamReq);
    }
}
