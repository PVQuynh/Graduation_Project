package com.chat.chat_server.service;

import com.chat.chat_server.dto.request.ConversationReq;
import com.chat.chat_server.dto.request.UpdateConversationReq;
import com.chat.chat_server.dto.response.ConversationAndGrouAttachConvListRes;
import com.chat.chat_server.entity.Conversation;
import java.util.List;

public interface ConversationService {

    List<ConversationAndGrouAttachConvListRes> getMyList();

    Conversation getById(long conversationId);

    ConversationAndGrouAttachConvListRes getByContactId(long contactId);

    void updateConversation(UpdateConversationReq updateConversationReq);

    void deleteById(long conversationID);


    //
    //
    //
    Conversation createConversation(ConversationReq conversationReq);

    void save(Conversation conversation);

}
