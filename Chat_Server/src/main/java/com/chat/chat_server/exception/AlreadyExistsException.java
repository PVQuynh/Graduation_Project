package com.chat.chat_server.exception;

public class AlreadyExistsException extends  RuntimeException{
    public AlreadyExistsException() {
        super("Already Exists Exception");
    }
}
