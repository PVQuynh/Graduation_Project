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

    @Query("select distinct  cv from Conversation cv inner join GroupMember gm on cv.id = gm.conversation.id "
            + "inner join GroupMember gm1 on gm1.conversation.id= cv.id "
            + "where (cv.conversationType = 'SINGLE') and ((gm.contact.email = :email and gm1.contact.id = :contactId) or "
            + "(gm1.contact.email = :email and gm.contact.id = :contactId))"
            + " GROUP BY cv HAVING COUNT(gm) = 2" )
    Conversation getConversationByContractId(@Param("email") String email, @Param("contactId") long contactId);

}
