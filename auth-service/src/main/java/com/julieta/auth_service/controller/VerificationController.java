package com.julieta.auth_service.controller;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/users/verify")
public class VerificationController {

    @PostMapping("/phoneNumber")
    public void verifyPhoneNumber() {

    }

    @PostMapping("/identity")
    public void verifyIdentity(){

    }

}
