package com.example.hust_learning_server.exception;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ErrorResponse {
    public String message;
    public Integer code;
    public Integer status = 0;
}
