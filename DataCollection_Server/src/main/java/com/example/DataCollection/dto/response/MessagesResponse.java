package com.example.DataCollection.dto.response;

import lombok.Data;
import org.springframework.stereotype.Component;

@Data
@Component
public class MessagesResponse {
    public int code = 200;
    public String message = "Successfully";
    public Object data;
    public MessagesResponse() {
        code=200;
        message="Successfully";
    }
}
