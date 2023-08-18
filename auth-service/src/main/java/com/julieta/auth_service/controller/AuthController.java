package com.julieta.auth_service.controller;

import com.julieta.auth_service.dto.AuthenticationRequest;
import com.julieta.auth_service.dto.AuthenticationResponse;
import com.julieta.auth_service.dto.RegistrationRequest;
import com.julieta.auth_service.exception.DuplicateEmailException;
import com.julieta.auth_service.exception.DuplicateIdException;
import com.julieta.auth_service.exception.DuplicatePhoneNumberException;
import com.julieta.auth_service.jwt.JwtTokenProvider;
import com.julieta.auth_service.service.AuthService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.security.sasl.AuthenticationException;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/auth")
public class AuthController {

    private final JwtTokenProvider jwtTokenProvider;
    private final AuthService userService;

    @PostMapping("/register")
    public ResponseEntity<Object> registerUser(@RequestBody @Valid RegistrationRequest request) throws DuplicateIdException, DuplicatePhoneNumberException, DuplicateEmailException {
        AuthenticationResponse response = userService.registerUser(request);
        return ResponseEntity.ok(response);
    }

    @PostMapping("/login")
    public ResponseEntity<Object> loginUser(@RequestBody @Valid AuthenticationRequest request) throws AuthenticationException {
        AuthenticationResponse response = userService.loginUser(request);
        return ResponseEntity.ok(response);
    }
}
