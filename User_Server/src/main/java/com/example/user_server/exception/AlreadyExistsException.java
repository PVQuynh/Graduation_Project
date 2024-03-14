package com.example.user_server.exception;

public class AlreadyExistsException extends  RuntimeException{
    public AlreadyExistsException() {
        super("Already Exists Exception");
    }
}
