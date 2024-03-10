package com.chat.websocket.repository;


import com.chat.websocket.entity.GroupMember;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface GroupMemberRepository extends JpaRepository<GroupMember,Long> {

    @Query("SELECT gm FROM GroupMember gm WHERE gm.conversation.id = :conversationId")
    List<GroupMember> findGroupMembersByConversationId(@Param("conversationId") long conversationId);

    @Query("select gm from GroupMember gm where gm.contact.id = :contactId and gm.conversation.id = :conversationId" )
    Optional<GroupMember> findByContactIdAndConversationId(@Param("contactId") long contactId,@Param("conversationId") long conversationId);
}
