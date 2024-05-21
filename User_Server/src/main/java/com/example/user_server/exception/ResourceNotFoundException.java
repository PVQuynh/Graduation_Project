package com.example.user_server.exception;

public class ResourceNotFoundException extends  RuntimeException{
    public ResourceNotFoundException() {
        super("Resource Not Found Exception");
    }
}
