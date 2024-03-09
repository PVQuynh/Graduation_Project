package com.chat.websocket.service;

import com.chat.websocket.dto.request.ConversationReq;
import com.chat.websocket.dto.request.UpdateConversationReq;
import com.chat.websocket.dto.response.ConversationAndGrouAttachConvListRes;
import com.chat.websocket.entity.Conversation;
import java.util.List;

public interface ConversationService {

    List<ConversationAndGrouAttachConvListRes> getMyList();

    Conversation getById(long conversationId);

    ConversationAndGrouAttachConvListRes getByContactId(long contactId);

    Conversation createConversation(ConversationReq conversationReq);

    void updateConversation(long id, UpdateConversationReq updateConversationReq);

    void deleteById(long conversationID);

}
