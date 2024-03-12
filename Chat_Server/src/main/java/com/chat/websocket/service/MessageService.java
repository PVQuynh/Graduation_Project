package com.chat.websocket.service;

import com.chat.websocket.dto.request.MessageLimitReq;
import com.chat.websocket.dto.request.MessageReq;
import com.chat.websocket.dto.request.UpdateMessageReq;
import com.chat.websocket.dto.response.MessageRes;
import java.util.List;

public interface MessageService {

  List<MessageRes> getAllMessageConversation(long conversationId);

  List<MessageRes> messageLimits(MessageLimitReq messageLimitReq);

  void saveMessage(long conversationId,MessageReq messageReq);

  void setSeenForMessage(long conversationId);

  void setSeenForMessageByContactId(long conversationId, long contactId);

  void updateMessage(UpdateMessageReq updateMessageReq);

  void deleteMessage(long id);
}
