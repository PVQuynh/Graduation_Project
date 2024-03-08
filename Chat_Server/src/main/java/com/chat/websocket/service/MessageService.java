package com.chat.websocket.service;

import com.chat.websocket.dto.response.MessageRes;
import java.util.List;

public interface MessageService {

  List<MessageRes> getAllMessageConversation(long conversationId);

  void deleteMessage(long id);
}
