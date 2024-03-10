package com.chat.websocket.dto.response;

import com.chat.websocket.enum_constant.MessageType;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class LastMessageRes {

    private String content;

    @Enumerated(EnumType.STRING)
    private MessageType messageType;

    private String mediaLocation;

    private int status;

    private Date created;

    private String contactName;
}
