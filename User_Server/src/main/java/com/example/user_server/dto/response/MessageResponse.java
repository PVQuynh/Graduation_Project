package com.example.user_server.dto.response;

import com.example.user_server.constant.HTTPCode;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@Data
@AllArgsConstructor
@Builder
public class MessageResponse {

    public int code = HTTPCode.SUCCESS;

    public String message = "Successfully!";

    public Object data;

    public MessageResponse() {
        code = HTTPCode.SUCCESS;
        message = "Successfully!";
    }



}
