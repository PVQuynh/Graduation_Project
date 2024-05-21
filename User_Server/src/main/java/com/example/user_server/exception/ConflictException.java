package com.example.user_server.exception;

public class ConflictException extends  RuntimeException{
    public ConflictException() {
        super("Conflict Exception");
    }
}
