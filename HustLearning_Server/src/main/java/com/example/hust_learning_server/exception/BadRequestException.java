package com.example.hust_learning_server.exception;

public class BadRequestException extends  RuntimeException{
    public BadRequestException() {
        super("Bad Request Exception");
    }
}
