package com.chat.websocket.controller;

import com.chat.websocket.dto.request.ContactReq;
import com.chat.websocket.dto.request.MessageLimitReq;
import com.chat.websocket.dto.request.UpdateMessageReq;
import com.chat.websocket.dto.response.MessageRes;
import com.chat.websocket.dto.response.MessageResponse;
import com.chat.websocket.service.MessageService;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/messages")
@RequiredArgsConstructor
public class MessageController {

  private final MessageService messageService;

  @GetMapping("{conversationId}")
  public List<MessageRes> getAllMessageConversation(@PathVariable  int conversationId) {
    try {
      return messageService.getAllMessageConversation(conversationId);
    } catch (Exception ex) {
      return null;
    }
  }

  @PostMapping("limits-conversation")
  public List<MessageRes> getMessageLimits(@RequestBody MessageLimitReq messageLimitReq) {
    try {
      return messageService.messageLimits(messageLimitReq);
    } catch (Exception ex) {
      return null;
    }
  }

  @PutMapping
  public MessageResponse updateMessage(@RequestBody UpdateMessageReq updateMessageReq) {
    MessageResponse ms = new MessageResponse();
    try {
      messageService.updateMessage(updateMessageReq);
    } catch (Exception ex) {
      ms.message = ex.getMessage();
      ms.code = 5000;
    }
    return ms;
  }

  @DeleteMapping("{id}")
  public MessageResponse deleteMessage(@PathVariable long id) {
    MessageResponse ms = new MessageResponse();
    try {
      messageService.deleteMessage(id);
    } catch (Exception ex) {
      ms.message = ex.getMessage();
      ms.code = 5000;
    }
    return ms;
  }
}
