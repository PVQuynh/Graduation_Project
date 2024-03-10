package com.chat.websocket.dto.response;

import java.time.LocalDateTime;
import java.util.Date;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class MessageRes {

  private String content;

  private String messageType;

  private String mediaLocation;

  private int status;

  private Date created;

  private long conversationId;

  private long contactId;
}
