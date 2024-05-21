package com.example.user_server.exception;

public class BadRequestException extends  RuntimeException{
    public BadRequestException() {
        super("Bad Request Exception");
    }
}
