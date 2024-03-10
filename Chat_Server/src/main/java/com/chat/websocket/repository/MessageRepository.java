package com.chat.websocket.repository;


import com.chat.websocket.entity.Message;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface MessageRepository extends JpaRepository<Message, Long> {

    @Query("select ms from Message ms   where ms.groupMember.conversation.id = :conversationId")
    List<Message> getMessageByConversationId(@Param("conversationId") long conversationId);

    @Query("update Message ms set ms.status = 200"
            + "where ms.groupMember.conversation.id =: conversationId and ms.status = 100 and ms.groupMember.contact.email <>: email")
    void setSeenForMessage(@Param("conversationId") long conversationId, @Param("email") String email);

}


