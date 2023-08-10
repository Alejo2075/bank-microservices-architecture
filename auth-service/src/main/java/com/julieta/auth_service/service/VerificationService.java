package com.julieta.auth_service.service;

import com.julieta.auth_service.model.User;

public interface VerificationService {

    void sendVerificationCode(User user, String code);
}
