package com.example.hust_learning_server.exception;

public class AlreadyExistsException extends  RuntimeException{
    public AlreadyExistsException() {
        super("Already Exists Exception");
    }
}
