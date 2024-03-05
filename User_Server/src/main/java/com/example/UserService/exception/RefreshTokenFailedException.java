package com.example.UserService.exception;

public class RefreshTokenFailedException extends  RuntimeException {
    public  RefreshTokenFailedException(String message) {
        super(message);
    }
}
