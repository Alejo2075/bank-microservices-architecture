package com.julieta.verificationservice.controller;

import com.julieta.auth_service.dto.VerificationEmailRequest;
import com.julieta.auth_service.service.VerificationService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/verify")
public class VerificationController {

    private final VerificationService verificationService;

    @PostMapping("/email")
    public void verifyEmail(VerificationEmailRequest request) {

    }

    @PostMapping("/identity")
    public void verifyIdentity(){

    }

}
