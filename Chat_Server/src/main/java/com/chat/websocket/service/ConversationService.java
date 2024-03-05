package com.chat.websocket.service;

import com.chat.websocket.dto.request.CreateConversationReq;
import com.chat.websocket.dto.response.GetListConversationRes;
import com.chat.websocket.entity.Conversation;
import java.util.List;

public interface ConversationService {
    Conversation createConversation(CreateConversationReq createConversationReq);

    void deleteById(long conversationID);

    List<GetListConversationRes> getMyList();

    GetListConversationRes getByContactId(long contactId);

    Conversation getById(long conversationId);
}
