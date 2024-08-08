package com.revature.exceptions;

public class TooFewExcepetion extends Exception{

    public TooFewExcepetion(String message) {
        super(message);
    }

    public TooFewExcepetion(String message, Throwable cause) {
        super(message, cause);
    }
}
