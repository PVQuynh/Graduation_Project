package com.chat.websocket.service;

import com.chat.websocket.dto.request.MessageReq;

public interface ChatService {
    void sendMessage(MessageReq messageReq, int conversationID);
}
