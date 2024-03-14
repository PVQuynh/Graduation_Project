package com.chat.chat_server.controller;

import com.chat.chat_server.constant.ExceptionConstant;
import com.chat.chat_server.constant.HTTPCode;
import com.chat.chat_server.dto.request.MessageLimitReq;
import com.chat.chat_server.dto.request.UpdateMessageReq;
import com.chat.chat_server.dto.response.MessageRes;
import com.chat.chat_server.dto.response.MessageResponse;
import com.chat.chat_server.service.MessageService;
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
      ms.code = HTTPCode.INTERNAL_SERVER_ERROR;
      ms.message = ExceptionConstant.INTERNAL_SERVER_ERROR;
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
