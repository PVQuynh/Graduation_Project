package com.chat.chat_server.exception;

public class BusinessLogicException extends  RuntimeException{
    public BusinessLogicException() {
        super("Business Logic Exception ");
    }
    public BusinessLogicException(String message) {
        super(message);
    }
}
