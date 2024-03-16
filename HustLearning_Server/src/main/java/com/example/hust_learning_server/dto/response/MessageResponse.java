package com.example.hust_learning_server.dto.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.http.HttpStatus;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class MessageResponse {

    public int code = HttpStatus.OK.value();

    public String message = HttpStatus.OK.getReasonPhrase();

    public Object data;

}
