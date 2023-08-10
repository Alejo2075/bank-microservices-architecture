package com.julieta.auth_service.exception;

public class NotSendVerificationCodeException extends Exception{

    public NotSendVerificationCodeException(String message){
        super(message);
    }
}
