package com.chat.websocket.controller;

import com.chat.websocket.dto.response.ChatMessageRes;
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
  public List<ChatMessageRes> getAllMessageConversation(@PathVariable  int conversationId) {
    return messageService.getAllMessageConversation(conversationId);
  }

  @DeleteMapping("{id}")
  public MessageResponse deleteMessageConversation(@PathVariable long id) {
    MessageResponse ms = new MessageResponse();
    try {
      messageService.deleteMessageConversation(id);
    } catch (Exception ex) {
      ms.message = ex.getMessage();
      ms.code = 5000;
    }
    return ms;

  }
}
