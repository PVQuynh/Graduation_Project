package com.chat.chat_server.repository;


import com.chat.chat_server.entity.Message;

import java.util.List;

import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

public interface MessageRepository extends JpaRepository<Message, Long> {

    @Query("select ms from Message ms where ms.groupMember.conversation.id = :conversationId order by ms.created desc")
    List<Message> getMessageByConversationId(@Param("conversationId") long conversationId);

    @Query("select ms from Message ms where ms.groupMember.conversation.id = :conversationId order by ms.created desc")
    List<Message> findMessageLimitsByConversationId(@Param("conversationId") long conversationId, Pageable pageable);

    @Modifying
    @Transactional
    @Query("UPDATE Message ms SET ms.status = 200 " +
            "WHERE ms.groupMember.conversation.id = :conversationId " +
            "AND ms.status = 100 " +
            "AND ms.groupMember.contact.email != :email")
    void setSeenForMessage(@Param("conversationId") long conversationId, @Param("email") String email);

    @Modifying
    @Transactional
    @Query("UPDATE Message ms SET ms.status = 200 " +
            "WHERE ms.status = 100" +
            "AND ms.groupMember.conversation.id = :conversationId " +
            "AND ms.groupMember.contact.id != :contactId")
    void setSeenForMessageByContactId(@Param("conversationId") long conversationId, @Param("contactId") long contactId);

}


