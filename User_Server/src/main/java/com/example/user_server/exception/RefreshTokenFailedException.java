package com.example.user_server.exception;

public class RefreshTokenFailedException extends  RuntimeException {
    public  RefreshTokenFailedException(String message) {
        super(message);
    }
}
