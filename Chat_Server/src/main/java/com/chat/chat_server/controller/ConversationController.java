package com.chat.chat_server.controller;

import com.chat.chat_server.dto.request.ConversationReq;
import com.chat.chat_server.dto.request.UpdateConversationReq;
import com.chat.chat_server.dto.response.ConversationAndGrouAttachConvListRes;
import com.chat.chat_server.dto.response.MessageResponse;
import com.chat.chat_server.service.ConversationService;

import java.util.List;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/conversations")
@RequiredArgsConstructor
public class ConversationController {

    private final ConversationService conversationService;

    @GetMapping("/all-me")
    public MessageResponse getMyListConversation() {
        MessageResponse ms = new MessageResponse();

        try {
            ms.data = conversationService.getMyList();
        } catch (Exception ex) {
            ms.code = HttpStatus.NOT_FOUND.value();
            ms.message = HttpStatus.NOT_FOUND.getReasonPhrase();
        }

        return ms;
    }

    @GetMapping("/{contactId}")
    public MessageResponse getConversationByContactId(@PathVariable long contactId) {
        MessageResponse ms = new MessageResponse();

        try {
            ms.data = conversationService.getByContactId(contactId);
        } catch (Exception ex) {
            ms.code = HttpStatus.NOT_FOUND.value();
            ms.message = HttpStatus.NOT_FOUND.getReasonPhrase();
        }

        return ms;
    }

    @PostMapping
    public MessageResponse createConversation(
            @RequestBody ConversationReq conversationReq) {
        MessageResponse ms = new MessageResponse();

        try {
            conversationService.createConversation(conversationReq);
        } catch (Exception e) {
            ms.code = HttpStatus.INTERNAL_SERVER_ERROR.value();
            ms.message = HttpStatus.INTERNAL_SERVER_ERROR.getReasonPhrase();
        }

        return ms;
    }

    @PutMapping
    public MessageResponse updateConversation(@RequestBody UpdateConversationReq updateConversationReq) {
        MessageResponse ms = new MessageResponse();
        try {
            conversationService.updateConversation(updateConversationReq);
        } catch (Exception e) {
            ms.code = HttpStatus.INTERNAL_SERVER_ERROR.value();
            ms.message = HttpStatus.INTERNAL_SERVER_ERROR.getReasonPhrase();
        }

        return ms;
    }

    @DeleteMapping("/{id}")
    public MessageResponse deleteConversation(@PathVariable("id") int id) {
        MessageResponse ms = new MessageResponse();

        try {
            conversationService.deleteById(id);
        } catch (Exception ex) {
            ms.code = HttpStatus.INTERNAL_SERVER_ERROR.value();
            ms.message = HttpStatus.INTERNAL_SERVER_ERROR.getReasonPhrase();
        }

        return ms;
    }
}
