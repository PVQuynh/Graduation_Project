package com.chat.websocket.dto.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

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
