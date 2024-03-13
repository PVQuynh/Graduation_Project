package com.example.HustLearning.controller;

import com.example.HustLearning.constant.ExceptionConstant;
import com.example.HustLearning.constant.HTTPCode;
import com.example.HustLearning.dto.PageDTO;
import com.example.HustLearning.dto.request.SearchParamReq;
import com.example.HustLearning.dto.request.UpdateVocabReq;
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
    public List<VocabRes> getAllVocabularies(@PathVariable("topicId") long topicId) {
        try {
            return vocabularyService.getVocabulariesByTopicId(topicId);

        } catch (Exception ex) {
          return  null;
        }
    }

    @PostMapping("/api/search")
    public PageDTO<VocabRes> GetLists(@RequestBody SearchParamReq searchParamReq){
        return  vocabularyService.search(searchParamReq);
    }

    @PostMapping
    public MessagesResponse addVocabulary(@RequestBody VocabReq vocabReq) {
        MessagesResponse ms = new MessagesResponse();
        try {
            vocabularyService.addVocabulary(vocabReq);
        } catch (Exception ex) {
            ms.code = HTTPCode.INTERAL_SERVER_ERROR;
            ms.message = ex.getMessage();
        }
        return ms;
    }

    @PutMapping
    public MessagesResponse updateVocabulary(@RequestBody UpdateVocabReq updateVocabReq) {
        MessagesResponse ms = new MessagesResponse();
        try {
            vocabularyService.updateVocabulary(updateVocabReq);
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
