package com.jayway.hopelink.user.exception;

public class AlreadyUserExistsException extends RuntimeException{
    public AlreadyUserExistsException(String message){
        super(String.format("Already user exists: %s", message));
    }
}
