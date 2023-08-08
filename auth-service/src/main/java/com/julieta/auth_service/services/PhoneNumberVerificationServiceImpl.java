package com.julieta.auth_service.services;

import com.julieta.auth_service.repositories.VerificationCodeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PhoneNumberVerificationServiceImpl implements PhoneNumberVerificationService {

    private final VerificationCodeRepository codeRepository;

    @Autowired
    public PhoneNumberVerificationServiceImpl(VerificationCodeRepository codeRepository) {
        this.codeRepository = codeRepository;
    }

    @Override
    public boolean verifyPhoneNumber(String userId, String code) {
        return true;
    }
}
