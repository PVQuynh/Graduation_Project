package com.example.hust_learning_server.dto.response;

import com.fasterxml.jackson.annotation.JsonProperty;
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
    public String message = HttpStatus.OK.getReasonPhrase();
    public int code = 200;
    public int status = 1;
    public Object data;
}
