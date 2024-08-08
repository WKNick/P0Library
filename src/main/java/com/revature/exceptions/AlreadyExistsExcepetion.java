package com.revature.exceptions;

public class AlreadyExistsExcepetion extends Exception{

    public AlreadyExistsExcepetion(String message) {
        super(message);
    }

    public AlreadyExistsExcepetion(String message, Throwable cause) {
        super(message, cause);
    }
}
