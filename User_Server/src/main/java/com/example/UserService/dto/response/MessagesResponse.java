package com.example.UserService.dto.response;

import lombok.Data;
import org.springframework.stereotype.Component;

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
