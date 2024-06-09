package com.example.hust_learning_server.controller;

import com.example.hust_learning_server.dto.PageDTO;
import com.example.hust_learning_server.dto.request.SearchTopicParamReq;
import com.example.hust_learning_server.dto.request.TopicReq;
import com.example.hust_learning_server.dto.request.UpdateTopicReq;
import com.example.hust_learning_server.dto.response.MessageResponse;
import com.example.hust_learning_server.dto.response.TopicRes;
import com.example.hust_learning_server.service.TopicService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("topics")
public class TopicController {
    private final TopicService topicService;

    @GetMapping("/all")
    public ResponseEntity<MessageResponse> getAllTopic(
        @RequestParam(required = false, defaultValue = "0") long classRoomId,
        @RequestParam(required = false, defaultValue = "") String isPrivate,
        @RequestParam(required = false, defaultValue = "") String contentSearch
    ) {
        MessageResponse ms = new MessageResponse();
        ms.data = topicService.getAllTopics(classRoomId, isPrivate, contentSearch);
        return ResponseEntity.ok(ms);
    }


    @GetMapping("/all-common/{classRoomId}")
    public ResponseEntity<MessageResponse> getAllCommonTopic(@PathVariable long classRoomId) {
        MessageResponse ms = new MessageResponse();
        ms.data = topicService.getAllCommonTopics(classRoomId);
        return ResponseEntity.ok(ms);
    }

    @GetMapping("/all-private/{classRoomId}")
    public ResponseEntity<MessageResponse> getAllPrivateTopic(@PathVariable long classRoomId) {
        MessageResponse ms = new MessageResponse();
        ms.data = topicService.getAllPrivateTopics(classRoomId);
        return ResponseEntity.ok(ms);
    }

    @PostMapping("/search")
    public PageDTO<TopicRes> getList(@RequestBody SearchTopicParamReq searchTopicParamReq) {
        return topicService.search(searchTopicParamReq);
    }

    @GetMapping("/search/v2")
    public ResponseEntity<MessageResponse> getList_v2(
            @RequestParam(defaultValue = "1", required = true) int page,
            @RequestParam(defaultValue = "10", required = true) int size,
            @RequestParam(required = true) String text,
            @RequestParam(required = false) boolean ascending,
            @RequestParam(required = false) String orderBy
    ) {
        MessageResponse ms = new MessageResponse();
        ms.data = topicService.searchV2(page, size, text, ascending, orderBy);
        return ResponseEntity.ok(ms);
    }

    @PostMapping
    public ResponseEntity<MessageResponse> addTopic(@RequestBody TopicReq topicReq) {
        MessageResponse ms = new MessageResponse();
        topicService.addTopic(topicReq);
        return ResponseEntity.ok(ms);

    }

    @PutMapping
    public ResponseEntity<MessageResponse> updateTopic(@RequestBody UpdateTopicReq updateTopicReq) {
        MessageResponse ms = new MessageResponse();
        topicService.updateTopic(updateTopicReq);
        return ResponseEntity.ok(ms);

    }

    @DeleteMapping("/{id}")
    public ResponseEntity<MessageResponse> deleteTopic(@PathVariable("id") long id) {
        MessageResponse ms = new MessageResponse();
        topicService.deleteTopicById(id);
        return ResponseEntity.ok(ms);
    }

}
