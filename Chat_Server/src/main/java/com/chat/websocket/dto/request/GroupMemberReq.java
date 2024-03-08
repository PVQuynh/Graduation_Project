package com.chat.websocket.dto.request;

import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Data
public class GroupMemberReq {

  private long conversationId;
  private List<Long> contactIds;
}
