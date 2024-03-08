package com.chat.websocket.service;

import com.chat.websocket.dto.request.ConversationReq;
import com.chat.websocket.dto.response.ConversationAllMeRes;
import com.chat.websocket.entity.Conversation;
import java.util.List;

public interface ConversationService {
    Conversation createConversation(ConversationReq conversationReq);

    void deleteById(long conversationID);

    List<ConversationAllMeRes> getMyList();

    ConversationAllMeRes getByContactId(long contactId);

    Conversation getById(long conversationId);
}
