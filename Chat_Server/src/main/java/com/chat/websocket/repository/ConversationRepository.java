package com.chat.websocket.repository;

import com.chat.websocket.entity.Conversation;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface ConversationRepository extends JpaRepository<Conversation, Long> {
    @Query("select cv from Conversation cv inner join GroupMember gm on cv.id = gm.conversation.id "
            + " where gm.contact.email = :email")
    List<Conversation> getMyConversationList(@Param("email") String email);

    @Query("select cv from Conversation cv "
            + "inner join GroupMember gm ON cv.id = gm.conversation.id "
            + "WHERE (gm.contact.id = :contactId)"
            + "GROUP BY cv HAVING COUNT(gm) = 2")
    Conversation getConversationByContractId(@Param("contactId") long contactId);

}
