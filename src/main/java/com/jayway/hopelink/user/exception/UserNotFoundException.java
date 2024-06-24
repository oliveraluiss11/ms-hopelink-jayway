package com.jayway.hopelink.user.exception;

public class UserNotFoundException extends RuntimeException{
    public UserNotFoundException(String message){
        super(String.format("User not found by document number: %s", message));
    }
}
