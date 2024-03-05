package com.chat.websocket.repository;


import com.chat.websocket.entity.Message;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface MessageRepository extends JpaRepository<Message, Long> {

  @Query("select ms from Message ms   where ms.groupMember.conversation.id = :conversationId")
  List<Message> getMessageByConversationId(@Param("conversationId") long conversationId);
}
