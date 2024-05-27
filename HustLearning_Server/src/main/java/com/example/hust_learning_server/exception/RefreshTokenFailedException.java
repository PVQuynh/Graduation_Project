package com.example.hust_learning_server.exception;

public class RefreshTokenFailedException extends  RuntimeException {
    public  RefreshTokenFailedException(String message) {
        super(message);
    }
}
