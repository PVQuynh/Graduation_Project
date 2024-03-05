package com.chat.websocket.service;

import com.chat.websocket.dto.response.ChatMessageRes;
import java.util.List;

public interface MessageService {

  List<ChatMessageRes> getAllMessageConversation(long conversationId);

  void deleteMessageConversation(long messageId);
}
