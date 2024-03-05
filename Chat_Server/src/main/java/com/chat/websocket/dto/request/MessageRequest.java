package com.chat.websocket.dto.request;

import com.chat.websocket.enum_constant.MessageType;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;


@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class MessageRequest {

    private int id;

    private String content;

    private String messageType;

    private String mediaLocation;

}
