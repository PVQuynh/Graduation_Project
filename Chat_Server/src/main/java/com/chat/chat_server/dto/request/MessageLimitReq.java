package com.chat.chat_server.dto.request;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class MessageLimitReq {
    public  int page =1 ;
    public  int size =10;
    public  long conversationId;
}
