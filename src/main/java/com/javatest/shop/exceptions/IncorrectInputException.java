package com.javatest.shop.exceptions;

public class IncorrectInputException extends RuntimeException {
    public IncorrectInputException(String input){
        super("Incorrect Input :"+input);
    }
}
