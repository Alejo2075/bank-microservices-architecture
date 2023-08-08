package com.julieta.auth_service.services;

public interface SmsService {

    void sendVerificationCode(String destinationPhoneNumber, String code);
}
