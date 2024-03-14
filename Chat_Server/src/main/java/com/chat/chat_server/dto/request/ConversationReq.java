package com.chat.chat_server.dto.request;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class ConversationReq {
    public String conversationName;

    public String conversationType;

    public List<Long>  contactIds;

}
