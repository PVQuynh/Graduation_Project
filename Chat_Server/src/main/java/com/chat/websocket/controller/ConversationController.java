package com.chat.websocket.controller;

import com.chat.websocket.dto.request.ConversationReq;
import com.chat.websocket.dto.request.UpdateConversationReq;
import com.chat.websocket.dto.response.ConversationAndGrouAttachConvListRes;
import com.chat.websocket.dto.response.MessageResponse;
import com.chat.websocket.service.ConversationService;

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
            ms.code = 5000;
            ms.message = e.getMessage();
        }

        return ms;
    }

    @PutMapping
    public MessageResponse updateConversation(@RequestBody UpdateConversationReq updateConversationReq) {
        MessageResponse ms = new MessageResponse();
        try {
            conversationService.updateConversation(updateConversationReq);
        } catch (Exception e) {
            ms.code = 5000;
            ms.message = e.getMessage();
        }

        return ms;
    }

    @DeleteMapping("/{id}")
    public MessageResponse deleteConversation(@PathVariable("id") int id) {
        MessageResponse ms = new MessageResponse();

        try {
            conversationService.deleteById(id);
        } catch (Exception ex) {
            ms.code = 5000;
            ms.message = ex.getMessage();
        }

        return ms;
    }
}
