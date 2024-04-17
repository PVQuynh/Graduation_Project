package com.chat.chat_server.controller;

import com.chat.chat_server.dto.request.MessageLimitReq;
import com.chat.chat_server.dto.request.MessageReq;
import com.chat.chat_server.dto.request.SaveMessageReq;
import com.chat.chat_server.dto.request.UpdateMessageReq;
import com.chat.chat_server.dto.response.MessageRes;
import com.chat.chat_server.dto.response.MessageResponse;
import com.chat.chat_server.service.MessageService;

import java.util.List;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/messages")
@RequiredArgsConstructor
public class MessageController {

    private final MessageService messageService;

    @GetMapping("/{conversationId}")
    public MessageResponse getAllMessageConversation(@PathVariable int conversationId) {
        MessageResponse ms = new MessageResponse();

        try {
            ms.data = messageService.getAllMessageConversation(conversationId);
        } catch (Exception ex) {
            ms.code = HttpStatus.NOT_FOUND.value();
            ms.message = HttpStatus.NOT_FOUND.getReasonPhrase();
        }

        return ms;
    }

    @PostMapping("/limits-conversation")
    public MessageResponse getMessageLimits(@RequestBody MessageLimitReq messageLimitReq) {
        MessageResponse ms = new MessageResponse();

        try {
            ms.data = messageService.messageLimits(messageLimitReq);
        } catch (Exception ex) {
            ms.code = HttpStatus.NOT_FOUND.value();
            ms.message = HttpStatus.NOT_FOUND.getReasonPhrase();
        }

        return ms;
    }

    @GetMapping("/limits-conversation/v2")
    public MessageResponse getMessageLimits_v2(
            @RequestParam(defaultValue = "1" ,required = true) int page,
            @RequestParam(defaultValue = "10", required = true) int size,
            @RequestParam(required = true) int conversationId
    ) {
        MessageResponse ms = new MessageResponse();

        try {
            ms.data = messageService.messageLimits_v2(page, size, conversationId);
        } catch (Exception ex) {
            ms.code = HttpStatus.NOT_FOUND.value();
            ms.message = HttpStatus.NOT_FOUND.getReasonPhrase();
        }

        return ms;
    }


    @PostMapping
    public MessageResponse saveMessage(@RequestBody SaveMessageReq saveMessageReq) {
        MessageResponse ms = new MessageResponse();
        try {
            messageService.saveMessage(saveMessageReq.getConversationId(), saveMessageReq.getMessageReq());
        } catch (Exception ex) {
            ms.code = HttpStatus.INTERNAL_SERVER_ERROR.value();
            ms.message = HttpStatus.INTERNAL_SERVER_ERROR.getReasonPhrase();
        }
        return ms;
    }

    @PutMapping
    public MessageResponse updateMessage(@RequestBody UpdateMessageReq updateMessageReq) {
        MessageResponse ms = new MessageResponse();
        try {
            messageService.updateMessage(updateMessageReq);
        } catch (Exception ex) {
            ms.code = HttpStatus.INTERNAL_SERVER_ERROR.value();
            ms.message = HttpStatus.INTERNAL_SERVER_ERROR.getReasonPhrase();
        }
        return ms;
    }

    @DeleteMapping("/{id}")
    public MessageResponse deleteMessage(@PathVariable long id) {
        MessageResponse ms = new MessageResponse();
        try {
            messageService.deleteMessage(id);
        } catch (Exception ex) {
            ms.code = HttpStatus.INTERNAL_SERVER_ERROR.value();
            ms.message = HttpStatus.INTERNAL_SERVER_ERROR.getReasonPhrase();
        }
        return ms;
    }
}
