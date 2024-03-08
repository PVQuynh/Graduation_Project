package com.chat.websocket.service;

import com.chat.websocket.dto.MessageDTO;

public interface ChatService {
    void sendMessage(MessageDTO messageDTO, int conversationID);
}
