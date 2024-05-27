package com.example.hust_learning_server.exception;

public class EmailNotFoundException extends  RuntimeException {
    public  EmailNotFoundException(String message) {
        super(message);
    }
}
