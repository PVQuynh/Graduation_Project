package com.example.user_server.exception;

public class UnAuthorizedException extends  RuntimeException {
    public  UnAuthorizedException(String message) {
        super(message);
    }
}
