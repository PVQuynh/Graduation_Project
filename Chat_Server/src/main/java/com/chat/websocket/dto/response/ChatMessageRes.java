package com.chat.websocket.dto.response;

import java.time.LocalDateTime;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class ChatMessageRes {

  private String content;

  private String messageType;

  private String mediaLocation;

  private int status;

  private LocalDateTime creationTime;

  private String name;

  private long conversationId;

  private long groupMemberId;
}
