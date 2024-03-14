package com.example.learning_server.controller;

import com.example.learning_server.constant.ExceptionConstant;
import com.example.learning_server.constant.HTTPCode;
import com.example.learning_server.dto.PageDTO;
import com.example.learning_server.dto.request.SearchParamReq;
import com.example.learning_server.dto.request.TopicReq;
import com.example.learning_server.dto.request.UpdateTopicReq;
import com.example.learning_server.dto.response.MessageResponse;
import com.example.learning_server.dto.response.TopicRes;
import com.example.learning_server.mapper.Impl.TopicMapperImpl;
import com.example.learning_server.service.TopicService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("topics")
public class TopicController {
    private final TopicService topicService;
    private final TopicMapperImpl topicMapper;

    @GetMapping("/all")
    public MessageResponse getAllTopic() {
        MessageResponse ms = new MessageResponse();

        try {
            ms.data = topicService.getAllTopic();
        } catch (Exception ex) {
            ms.code = HTTPCode.RESOURCE_NOT_FOUND;
            ms.message = ExceptionConstant.RESOURCE_NOT_FOUND;
        }

        return ms;
    }

    @PostMapping("/search")
    public PageDTO<TopicRes> GetLists(@RequestBody SearchParamReq searchParamReq){
        return topicService.search(searchParamReq);
    }

    @PostMapping
    public MessageResponse addTopic(@RequestBody @Valid TopicReq topicReq) {
        MessageResponse ms = new MessageResponse();

        try {
            topicService.addTopic(topicReq);
        } catch (Exception ex) {
            ms.code = HTTPCode.INTERNAL_SERVER_ERROR;
            ms.message = ex.getMessage(); // not fix
        }

        return ms;
    }

    @PutMapping
    public MessageResponse updateTopic(@RequestBody UpdateTopicReq updateTopicReq) {
        MessageResponse ms = new MessageResponse();

        try {
            topicService.updateTopic(updateTopicReq);
        } catch (Exception ex) {
            ms.code = HTTPCode.INTERNAL_SERVER_ERROR;
            ms.message = ExceptionConstant.INTERNAL_SERVER_ERROR;
        }

        return ms;
    }

    @DeleteMapping("/{id}")
    public MessageResponse deleteTopic(@PathVariable("id") long id) {
        MessageResponse ms = new MessageResponse();

        try {
            topicService.deleteTopicById(id);
        } catch (Exception ex) {
            ms.code = HTTPCode.INTERNAL_SERVER_ERROR;
            ms.message = ExceptionConstant.INTERNAL_SERVER_ERROR;
        }

        return ms;
    }

}
