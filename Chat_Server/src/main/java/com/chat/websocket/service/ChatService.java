package com.chat.websocket.service;

import com.chat.websocket.dto.request.MessageRequest;

public interface ChatService {
    void sendMessage(MessageRequest messageRequest, int conversationID);
}
