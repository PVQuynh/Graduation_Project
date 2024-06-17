package com.chat.chat_server.exception;

public class EmailNotFoundException extends  RuntimeException {
    public EmailNotFoundException(String message) {
        super(message);
    }
}
