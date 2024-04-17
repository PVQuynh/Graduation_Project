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
//    @Query(value = "UPDATE message " +
//            "SET message.status = 200 " +
//            "WHERE ( " +
//            "SELECT message.message_id, message.status, contact.email, conversation.conversation_id " +
//            "FROM message " +
//            "INNER JOIN group_member ON group_member.group_member_id = message.group_member_id " +
//            "INNER JOIN conversation ON conversation.conversation_id = group_member.conversation_id " +
//            "INNER JOIN contact ON contact.contact_id = group_member.contact_id " +
//            "WHERE " +
//            "conversation.conversation_id = :conversationId " +
//            "AND status = 100 " +
//            "AND contact.email = :email )",
//            nativeQuery = true)
    void setSeenForMessage(@Param("conversationId") long conversationId, @Param("email") String email);

    @Modifying
    @Transactional
    @Query("UPDATE Message ms SET ms.status = 200 " +
            "WHERE ms.status = 100" +
            "AND ms.groupMember.conversation.id = :conversationId " +
            "AND ms.groupMember.contact.id != :contactId")
//    @Query(value = "UPDATE message m " +
//            "INNER JOIN group_member gm ON gm.group_member_id = m.group_member_id " +
//            "INNER JOIN conversation cv ON cv.conversation_id = gm.conversation_id " +
//            "SET m.status = 200 " +
//            "WHERE cv.conversation_id = :conversationId " +
//            "AND gm.contact_id = :contactId " +
//            "AND m.status = 100",
//            nativeQuery = true)
    void setSeenForMessageByContactId(@Param("conversationId") long conversationId, @Param("contactId") long contactId);

}






