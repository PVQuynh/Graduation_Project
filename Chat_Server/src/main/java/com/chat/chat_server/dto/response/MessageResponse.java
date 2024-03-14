package com.chat.chat_server.dto.response;

import lombok.AllArgsConstructor;
import lombok.Data;

@AllArgsConstructor
@Data
public class MessageResponse {

    public int code = 200;

    public String message = "Successfully!";

    public Object data;

    public MessageResponse() {
        code =200;
        message = "Successfully!";
    }
}
