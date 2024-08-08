package com.revature.exceptions;

public class DoesNotExistExcepetion extends Exception{

    public DoesNotExistExcepetion(String message) {
        super(message);
    }

    public DoesNotExistExcepetion(String message, Throwable cause) {
        super(message, cause);
    }
}
