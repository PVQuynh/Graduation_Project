package com.chat.websocket.dto.response;

import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ConversationAndGrouAttachConvListRes {

  public long conversationId;

  public  List<GrouAttachConvRes> grouAttachConvResList;

}
