package com.chat.websocket.dto.request;

import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ConversationAndContactsRequest {
//    @Valid
//    private ConversationRequest conversationRequest;

    @Valid
    private List<ContactRequest> contactRequests;
}
