package com.julieta.auth_service.exception;

public class DuplicateEmailException extends Exception{

    public DuplicateEmailException(String message){
        super(message);
    }
}
