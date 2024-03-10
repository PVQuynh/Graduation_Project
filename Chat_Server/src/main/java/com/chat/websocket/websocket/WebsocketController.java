package com.chat.websocket.websocket;

import com.chat.websocket.dto.request.MessageReq;
import com.chat.websocket.service.MessageService;
import lombok.RequiredArgsConstructor;
import org.springframework.messaging.handler.annotation.DestinationVariable;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class WebsocketController {

    private final MessageService messageService;

    @MessageMapping("/chat/{conversationId}")
    @SendTo("/conversation/{conversationId}")
    public MessageReq sendMessage(@DestinationVariable int conversationId,
                                  @Payload MessageReq messageReq) {

//        messageService.setSeenForMessage(conversationId);
//        messageService.saveMessage(conversationId, messageReq);

        return messageReq;
    }


}
