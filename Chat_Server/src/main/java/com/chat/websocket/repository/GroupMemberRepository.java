package com.chat.websocket.repository;


import com.chat.websocket.entity.GroupMember;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Optional;

public interface GroupMemberRepository extends JpaRepository<GroupMember,Long> {
    @Query("select gm from GroupMember gm where gm.contact.email = :email and gm.conversation.id = :conversationId" )
    Optional<GroupMember> findByEmailAndConversationId(@Param("email") String email,@Param("conversationId") int conversationId);
}
