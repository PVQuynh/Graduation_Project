package com.example.user_server.dto.response;

import lombok.Data;

@Data
public class MessagesResponse {

    public int code = 200;

    public String message = "Successfully";

    public Object data;

    public MessagesResponse() {
        code=200;
        message="Successfully";
    }
}
