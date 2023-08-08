package com.julieta.auth_service.services;

public interface PhoneNumberVerificationService {

    public boolean verifyPhoneNumber(String userId, String code);
}
