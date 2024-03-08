package com.chat.websocket.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class MessageDTO {

    private int id;

    private String content;

    private String messageType;

    private String mediaLocation;

}
