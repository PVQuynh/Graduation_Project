package com.example.hust_learning_server.exception;

public class ConflictException extends  RuntimeException{
    public ConflictException() {
        super("Conflict Exception");
    }
}
