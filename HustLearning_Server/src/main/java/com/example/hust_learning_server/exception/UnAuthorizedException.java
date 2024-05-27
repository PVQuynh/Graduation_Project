package com.example.hust_learning_server.exception;

public class UnAuthorizedException extends  RuntimeException {
    public  UnAuthorizedException() {
        super("Unauthorized Exception");
    }
}
