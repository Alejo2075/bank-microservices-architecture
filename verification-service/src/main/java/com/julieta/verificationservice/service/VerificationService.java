package com.julieta.verificationservice.service;

import com.julieta.auth_service.model.Code;

public interface VerificationService {

    void sendVerificationEmailCode(Code userCode);

}
