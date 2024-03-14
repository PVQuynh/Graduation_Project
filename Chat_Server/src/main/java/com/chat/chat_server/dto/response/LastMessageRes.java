package com.chat.chat_server.dto.response;

import com.chat.chat_server.enum_constant.MessageType;
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

    private long messageId;

    private String content;

    @Enumerated(EnumType.STRING)
    private MessageType messageType;

    private String mediaLocation;

    private int status;

    private Date created;

    private String contactName;
}
