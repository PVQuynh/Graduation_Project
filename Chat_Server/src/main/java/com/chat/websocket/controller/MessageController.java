package com.chat.websocket.controller;

import com.chat.websocket.dto.request.ContactReq;
import com.chat.websocket.dto.response.MessageRes;
import com.chat.websocket.dto.response.MessageResponse;
import com.chat.websocket.service.MessageService;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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
