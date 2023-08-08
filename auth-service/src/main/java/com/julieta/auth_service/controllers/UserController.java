package com.julieta.auth_service.controllers;

import com.julieta.auth_service.dtos.*;
import com.julieta.auth_service.events.OnRegistrationCompleteEvent;
import com.julieta.auth_service.exceptions.*;
import com.julieta.auth_service.services.PhoneNumberVerificationService;
import com.julieta.auth_service.services.UserService;
import jakarta.validation.Valid;
import org.apache.http.HttpStatus;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/users")
public class UserController {

    private final UserService userService;
    private final ApplicationEventPublisher eventPublisher;
    private final PhoneNumberVerificationService verificationService;

    @Autowired
    public UserController(UserService userService, ApplicationEventPublisher eventPublisher,
                          PhoneNumberVerificationService verificationService) {
        this.userService = userService;
        this.eventPublisher = eventPublisher;
        this.verificationService = verificationService;
    }

    @PostMapping("/register")
    public ResponseEntity<Object> registerUser(@RequestBody @Valid RegistrationRequest request) {
        try {

            RegistrationResponse response = userService.registerUser(request);
            eventPublisher.publishEvent(new OnRegistrationCompleteEvent(request));

            return ResponseEntity.ok(response);

        }catch (DuplicateIdException e) {
            return createErrorResponse(HttpStatus.SC_BAD_REQUEST, e.getMessage());
        } catch (Exception e) {
            return createErrorResponse(HttpStatus.SC_INTERNAL_SERVER_ERROR, "Unknown error");
        }

    }

    @PostMapping("/verify")
    public PhoneNumberVerificationResponse verify(@RequestBody PhoneNumberVerificationRequest request) {
        boolean isVerified = verificationService.verifyPhoneNumber(request.getUserId(), request.getCode());
        return new PhoneNumberVerificationResponse(isVerified);
    }

    private ResponseEntity<Object> createErrorResponse(int status, String message) {
        ErrorResponse errorResponse = ErrorResponse.builder()
                .status(status)
                .message(message)
                .build();

        return new ResponseEntity<>(errorResponse, HttpStatusCode.valueOf(status));
    }
}
