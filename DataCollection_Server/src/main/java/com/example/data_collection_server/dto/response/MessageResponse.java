package com.example.data_collection_server.dto.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;

@Data
@Component
@AllArgsConstructor
@NoArgsConstructor
public class MessageResponse {
    public int code = HttpStatus.OK.value();
    public String message = HttpStatus.OK.getReasonPhrase();
    public Object data;
}
