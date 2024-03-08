package com.chat.websocket.controller;

import com.chat.websocket.dto.MessageDTO;
import com.chat.websocket.service.ChatService;
import lombok.RequiredArgsConstructor;
import org.springframework.messaging.handler.annotation.DestinationVariable;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class ChatController {

  private final ChatService chatService;

  // Send message and save
  @MessageMapping("/chat/{conversationID}")
  @SendTo("/conversation/{conversationID}")
  public MessageDTO sendMessage(@DestinationVariable int conversationID,
                                @Payload MessageDTO messageDTO) {

//      chatService.sendMessage(messageRequest, conversationID);


    return messageDTO;
  }


}
