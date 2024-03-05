package com.example.UserService.exception;

public class UnAuthorizedException extends  RuntimeException {
    public  UnAuthorizedException(String message) {
        super(message);
    }
}
