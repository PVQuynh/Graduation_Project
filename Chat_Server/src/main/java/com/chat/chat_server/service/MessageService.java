package com.chat.chat_server.service;

import com.chat.chat_server.dto.request.MessageLimitReq;
import com.chat.chat_server.dto.request.MessageReq;
import com.chat.chat_server.dto.request.UpdateMessageReq;
import com.chat.chat_server.dto.response.MessageRes;
import java.util.List;

public interface MessageService {

  List<MessageRes> getAllMessageConversation(long conversationId);

  List<MessageRes> messageLimits(MessageLimitReq messageLimitReq);

  List<MessageRes> messageLimits_v2(int page, int size, int conversationId);

  void saveMessage(long conversationId,MessageReq messageReq);

  void setSeenForMessageByContactId(long conversationId, long contactId);

  void updateMessage(UpdateMessageReq updateMessageReq);

  void deleteMessage(long id);

}
