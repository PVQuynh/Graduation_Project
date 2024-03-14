package com.chat.chat_server.dto.response;

import java.time.LocalDateTime;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class GrouAttachConvRes {

  public long contactId;

  public String contactName;

  public String avatarLocation;

  public String email;

  public LocalDateTime lastActivity;

  public LastMessageRes lastMessageRes;
}
