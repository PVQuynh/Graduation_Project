package com.chat.websocket.dto.response;

import java.time.LocalDateTime;
import java.util.Date;

import com.chat.websocket.enum_constant.MessageType;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class MessageRes {

  private long messageId;

  private String content;

  @Enumerated(EnumType.STRING)
  private MessageType messageType;

  private String mediaLocation;

  private int status;

  private Date created;

  private long conversationId;

  private long contactId;
}
