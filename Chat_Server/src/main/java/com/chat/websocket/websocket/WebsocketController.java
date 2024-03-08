package com.chat.websocket.websocket;

import com.chat.websocket.dto.request.MessageReq;
import com.chat.websocket.service.ChatService;
import lombok.RequiredArgsConstructor;
import org.springframework.messaging.handler.annotation.DestinationVariable;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class WebsocketController {

  private final ChatService chatService;

  @MessageMapping("/chat/{conversationId}")
  @SendTo("/conversation/{conversationId}")
  public MessageReq sendMessage(@DestinationVariable int conversationId,
                                @Payload MessageReq messageReq) {

//      chatService.sendMessage(messageRequest, conversationID);

    return messageReq;
  }


}
