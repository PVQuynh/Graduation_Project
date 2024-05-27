package com.example.hust_learning_server.exception;

public class ResourceNotFoundException extends  RuntimeException{
    public ResourceNotFoundException() {
        super("Resource Not Found Exception");
    }
}
