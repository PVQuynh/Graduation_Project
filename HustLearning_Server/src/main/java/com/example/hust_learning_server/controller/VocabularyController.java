package com.example.hust_learning_server.controller;

import com.example.hust_learning_server.dto.PageDTO;
import com.example.hust_learning_server.dto.request.*;
import com.example.hust_learning_server.dto.response.VocabularyRes;
import com.example.hust_learning_server.dto.response.MessageResponse;
import com.example.hust_learning_server.service.VocabularySerivce;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/vocabularies")
public class VocabularyController {

    private final VocabularySerivce vocabularyService;

    @GetMapping("/get-by-id/{id}")
    public ResponseEntity<MessageResponse> getVocabularyById(@PathVariable("id") long id) {
        MessageResponse ms = new MessageResponse();
        ms.data = vocabularyService.getById(id);
        return ResponseEntity.ok(ms);
    }

    @GetMapping("/all")
    public ResponseEntity<MessageResponse> getAllVocabulary(
        @RequestParam(required = false) Long topicId,
        @RequestParam(required = false) String content
    ) {
        MessageResponse ms = new MessageResponse();
        if (topicId != null) {
            if (content == null) {
                ms.data = vocabularyService.getVocabulariesByTopicId(topicId);
            } else {
                ms.data = vocabularyService.getVocabularyByTopicIdAndVocabularyTypeAndSearchContent(topicId, null, content);
            }
        } else {
            ms.data = vocabularyService.getAllVocabulary();
        }
        return ResponseEntity.ok(ms);
    }

    @GetMapping("/all/v2")
    public ResponseEntity<MessageResponse> getAllVocabularyV2(
        @RequestParam(required = false) Long topicId,
        @RequestParam(required = false) String vocabularyType,
        @RequestParam(required = false) String content
    ) {
        MessageResponse ms = new MessageResponse();
        if (topicId != null) {
            if (vocabularyType == null) {
                ms.data = vocabularyService.getVocabulariesByTopicId(topicId);
            } else {
                ms.data = vocabularyService.getVocabularyByTopicIdAndVocabularyTypeAndSearchContent(topicId, vocabularyType, content);
            }
        } else {
            ms.data = vocabularyService.getAllVocabulary();
        }
        return ResponseEntity.ok(ms);
    }


    @GetMapping("/{topicId}")
    public ResponseEntity<MessageResponse> getAllVocabulariesByTopicId(@PathVariable("topicId") long topicId) {
        MessageResponse ms = new MessageResponse();
        ms.data = vocabularyService.getVocabulariesByTopicId(topicId);
        return ResponseEntity.ok(ms);
    }

    @PostMapping("/get-by-content")
    public ResponseEntity<MessageResponse> getExactVocabularies(@RequestBody ExactVocabularyReq exactVocabularyReq) {
        MessageResponse ms = new MessageResponse();
        ms.data = vocabularyService.getExactVocabularies(exactVocabularyReq);
        return ResponseEntity.ok(ms);
    }

    @GetMapping("/get-by-content/v2")
    public ResponseEntity<MessageResponse> getExactVocabularies(
        @RequestParam(required = true) String content
    ) {
        MessageResponse ms = new MessageResponse();
        ms.data = vocabularyService.getVocabulariesByContent(content);
        return ResponseEntity.ok(ms);
    }

    @PostMapping("/limits-topic")
    public ResponseEntity<MessageResponse> getAllVocabularies(@RequestBody VocabularyLimitReq vocabularyLimitReq) {
        MessageResponse ms = new MessageResponse();
        ms.data = vocabularyService.vocabularyLimits(vocabularyLimitReq);
        return ResponseEntity.ok(ms);
    }

    @GetMapping("/limits-topic/v2")
    public ResponseEntity<MessageResponse> getVocabulariesLimits(
        @RequestParam(defaultValue = "1", required = true) int page,
        @RequestParam(defaultValue = "10", required = true) int size,
        @RequestParam(required = true) long topicId
    ) {
        MessageResponse ms = new MessageResponse();
        ms.data = vocabularyService.vocabularyLimitsTopic(page, size, topicId);
        return ResponseEntity.ok(ms);
    }

    @PostMapping("/search")
    public PageDTO<VocabularyRes> getList(@RequestBody SearchVocabularyParamReq searchVocabularyParamReq) {
        return vocabularyService.search(searchVocabularyParamReq);
    }

    @GetMapping("/search/v2")
    public ResponseEntity<MessageResponse> getList_v2(
        @RequestParam(defaultValue = "1", required = true) int page,
        @RequestParam(defaultValue = "10", required = true) int size,
        @RequestParam(required = true) String text,
        @RequestParam(required = false) boolean ascending,
        @RequestParam(required = false) String orderBy,
        @RequestParam(required = false) long topicId

    ) {
        MessageResponse ms = new MessageResponse();
        ms.data = vocabularyService.searchV2(page, size, text, ascending, orderBy, topicId);
        return ResponseEntity.ok(ms);
    }

    @PostMapping
    public ResponseEntity<MessageResponse> addVocabulary(@RequestBody VocabularyReq vocabularyReq) {
        MessageResponse ms = new MessageResponse();
        vocabularyService.addVocabulary(vocabularyReq);
        return ResponseEntity.ok(ms);
    }

    @PostMapping("/add-new-topic")
    public ResponseEntity<MessageResponse> addVocabularyToNewTopic(@RequestBody AddVocabularyToNewTopic addVocabularyToNewTopic) {
        MessageResponse ms = new MessageResponse();
        vocabularyService.addVocabularyToNewTopic(addVocabularyToNewTopic);
        return ResponseEntity.ok(ms);
    }

    @PostMapping("/add-vocab-list-to-new-topic")
    public ResponseEntity<MessageResponse> addVocabularyListToNewTopic(@RequestBody List<AddVocabularyToNewTopic> addVocabularyToNewTopicList) {
        MessageResponse ms = new MessageResponse();
        vocabularyService.addVocabularyListToNewTopic(addVocabularyToNewTopicList);
        return ResponseEntity.ok(ms);
    }

    @PostMapping("/add-vocab-list-to-new-topic/v2")
    public ResponseEntity<MessageResponse> addVocabularyListToNewTopic_v2(@RequestBody AddVocabularyListToNewTopic addVocabularyListToNewTopic) {
        MessageResponse ms = new MessageResponse();
        vocabularyService.addVocabularyListToNewTopic_v2(addVocabularyListToNewTopic);
        return ResponseEntity.ok(ms);
    }

    @PostMapping("/add-list")
    public ResponseEntity<MessageResponse> addVocabularyList(@RequestBody List<VocabularyReq> vocabularyReqList) {
        MessageResponse ms = new MessageResponse();
        vocabularyService.addVocabularyList(vocabularyReqList);
        return ResponseEntity.ok(ms);
    }

    @PutMapping
    public ResponseEntity<MessageResponse> updateVocabulary(@RequestBody UpdateVocabularyReq updateVocabularyReq) {
        MessageResponse ms = new MessageResponse();
        vocabularyService.updateVocabulary(updateVocabularyReq);
        return ResponseEntity.ok(ms);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<MessageResponse> deleteVocabularyById(@PathVariable("id") long id) {
        MessageResponse ms = new MessageResponse();
        vocabularyService.deleteById(id);
        return ResponseEntity.ok(ms);
    }


}
