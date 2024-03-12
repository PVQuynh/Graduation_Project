package com.example.HustLearning.controller;

import com.example.HustLearning.constant.ExceptionConstant;
import com.example.HustLearning.constant.HTTPCode;
import com.example.HustLearning.dto.PageDTO;
import com.example.HustLearning.dto.request.SearchParamReq;
import com.example.HustLearning.dto.request.TopicReq;
import com.example.HustLearning.dto.request.UpdateTopicReq;
import com.example.HustLearning.dto.response.MessagesResponse;
import com.example.HustLearning.dto.response.TopicRes;
import com.example.HustLearning.mapper.Impl.TopicMapperImpl;
import com.example.HustLearning.service.TopicService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("topics")
public class TopicController {
    private final TopicService topicService;
    private final TopicMapperImpl topicMapper;

    @GetMapping("/all")
    public List<TopicRes> getAllTopic() {
        try {
            return topicService.getAllTopic();
        } catch (Exception ex) {
            return null;
        }
    }

    @PostMapping("/search")
    public PageDTO<TopicRes> GetLists(@RequestBody SearchParamReq searchParamReq){
        return  topicService.search(searchParamReq);
    }

    @PostMapping
    public MessagesResponse addTopic(@RequestBody @Valid TopicReq topicReq) {
        MessagesResponse ms = new MessagesResponse();

        try {
            topicService.addTopic(topicReq);
        } catch (Exception ex) {
            ms.code = HTTPCode.INTERAL_SERVER_ERROR;
            ms.message = ExceptionConstant.INTERNAL_SERVER_ERROR;
        }

        return ms;
    }

    @PutMapping
    public MessagesResponse updateTopic(@RequestBody UpdateTopicReq updateTopicReq) {
        MessagesResponse ms = new MessagesResponse();

        try {
            topicService.updateTopic(updateTopicReq);
        } catch (Exception ex) {
            ms.code = HTTPCode.INTERAL_SERVER_ERROR;
            ms.message = ExceptionConstant.INTERNAL_SERVER_ERROR;
        }

        return ms;
    }


    @DeleteMapping("/{id}")
    public MessagesResponse deleteTopic(@PathVariable("id") long id) {
        MessagesResponse ms = new MessagesResponse();

        try {
            topicService.deleteTopicById(id);
        } catch (Exception ex) {
            ms.code = HTTPCode.INTERAL_SERVER_ERROR;
            ms.message = ExceptionConstant.INTERNAL_SERVER_ERROR;
        }

        return ms;
    }

}
