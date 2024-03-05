package com.example.HustLearning.dto.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@Data
@AllArgsConstructor
@Builder
public class MessagesResponse {

    public int code = 200;

    public String message = "Successfully";

    public Object data;

    public MessagesResponse() {
        code=200;
        message="Successfully";
    }



}
