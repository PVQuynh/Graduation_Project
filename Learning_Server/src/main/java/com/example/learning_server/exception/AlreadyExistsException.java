package com.example.learning_server.exception;

public class AlreadyExistsException extends  RuntimeException{
    public AlreadyExistsException() {
        super("Already Exists Exception");
    }
}
