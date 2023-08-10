package com.julieta.auth_service.exception;

public class DuplicatePhoneNumberException extends Exception{

    public DuplicatePhoneNumberException(String message){
        super(message);
    }
}
