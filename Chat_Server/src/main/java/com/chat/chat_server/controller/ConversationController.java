package com.chat.chat_server.controller;

import com.chat.chat_server.constant.ExceptionConstant;
import com.chat.chat_server.constant.HTTPCode;
import com.chat.chat_server.dto.request.ConversationReq;
import com.chat.chat_server.dto.request.UpdateConversationReq;
import com.chat.chat_server.dto.response.ConversationAndGrouAttachConvListRes;
import com.chat.chat_server.dto.response.MessageResponse;
import com.chat.chat_server.service.ConversationService;

import java.util.List;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/conversations")
@RequiredArgsConstructor
public class ConversationController {

    private final ConversationService conversationService;

    @GetMapping("/all-me")
    public List<ConversationAndGrouAttachConvListRes> getMyListConversation() {
        try {
            return conversationService.getMyList();
        } catch (Exception ex) {
            return null;
        }
    }

    @GetMapping("/{contactId}")
    public ConversationAndGrouAttachConvListRes getConversationByContactId(@PathVariable long contactId) {
        try {
            return conversationService.getByContactId(contactId);
        } catch (Exception ex) {
            return null;
        }
    }

    @PostMapping
    public MessageResponse createConversation(
            @RequestBody ConversationReq conversationReq) {
        MessageResponse ms = new MessageResponse();

        try {
            conversationService.createConversation(conversationReq);
        } catch (Exception e) {
            ms.code = HTTPCode.INTERNAL_SERVER_ERROR;
            ms.message = ExceptionConstant.INTERNAL_SERVER_ERROR;
        }

        return ms;
    }

    @PutMapping
    public MessageResponse updateConversation(@RequestBody UpdateConversationReq updateConversationReq) {
        MessageResponse ms = new MessageResponse();
        try {
            conversationService.updateConversation(updateConversationReq);
        } catch (Exception e) {
            ms.code = HTTPCode.INTERNAL_SERVER_ERROR;
            ms.message = ExceptionConstant.INTERNAL_SERVER_ERROR;
        }

        return ms;
    }

    @DeleteMapping("/{id}")
    public MessageResponse deleteConversation(@PathVariable("id") int id) {
        MessageResponse ms = new MessageResponse();

        try {
            conversationService.deleteById(id);
        } catch (Exception ex) {
            ms.code = HTTPCode.INTERNAL_SERVER_ERROR;
            ms.message = ExceptionConstant.INTERNAL_SERVER_ERROR;
        }

        return ms;
    }
}
