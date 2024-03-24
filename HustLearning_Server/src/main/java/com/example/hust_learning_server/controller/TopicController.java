package com.example.hust_learning_server.controller;

import com.example.hust_learning_server.dto.PageDTO;
import com.example.hust_learning_server.dto.request.SearchTopicParamReq;
import com.example.hust_learning_server.dto.request.TopicReq;
import com.example.hust_learning_server.dto.request.UpdateTopicReq;
import com.example.hust_learning_server.dto.response.MessageResponse;
import com.example.hust_learning_server.dto.response.TopicRes;
import com.example.hust_learning_server.exception.AlreadyExistsException;
import com.example.hust_learning_server.exception.BusinessLogicException;
import com.example.hust_learning_server.mapper.Impl.TopicMapperImpl;
import com.example.hust_learning_server.service.TopicService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
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
            ms.code = HttpStatus.NOT_FOUND.value();
            ms.message = HttpStatus.NOT_FOUND.getReasonPhrase();
        }

        return ms;
    }

    @PostMapping("/search")
    public PageDTO<TopicRes> GetLists(@RequestBody SearchTopicParamReq searchTopicParamReq) {
        return topicService.search(searchTopicParamReq);
    }

    @PostMapping
    public MessageResponse addTopic(@RequestBody @Valid TopicReq topicReq) {
        MessageResponse ms = new MessageResponse();

        try {
            topicService.addTopic(topicReq);
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
    public MessageResponse updateTopic(@RequestBody UpdateTopicReq updateTopicReq) {
        MessageResponse ms = new MessageResponse();

        try {
            topicService.updateTopic(updateTopicReq);
        } catch (Exception ex) {
            ms.code = HttpStatus.INTERNAL_SERVER_ERROR.value();
            ms.message = HttpStatus.INTERNAL_SERVER_ERROR.getReasonPhrase();
        }

        return ms;
    }

    @DeleteMapping("/{id}")
    public MessageResponse deleteTopic(@PathVariable("id") long id) {
        MessageResponse ms = new MessageResponse();

        try {
            topicService.deleteTopicById(id);
        } catch (Exception ex) {
            ms.code = HttpStatus.INTERNAL_SERVER_ERROR.value();
            ms.message = HttpStatus.INTERNAL_SERVER_ERROR.getReasonPhrase();
        }

        return ms;
    }

}
