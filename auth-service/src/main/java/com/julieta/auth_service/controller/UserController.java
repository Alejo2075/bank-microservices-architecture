package com.julieta.auth_service.controller;

import com.julieta.auth_service.dto.AuthenticationRequest;
import com.julieta.auth_service.dto.AuthenticationResponse;
import com.julieta.auth_service.dto.ErrorResponse;
import com.julieta.auth_service.dto.RegistrationRequest;
import com.julieta.auth_service.event.OnRegistrationCompleteEvent;
import com.julieta.auth_service.exception.DuplicateIdException;
import com.julieta.auth_service.exception.DuplicatePhoneNumberException;
import com.julieta.auth_service.service.UserService;
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

    @Autowired
    public UserController(UserService userService, ApplicationEventPublisher eventPublisher) {
        this.userService = userService;
        this.eventPublisher = eventPublisher;
    }

    @PostMapping("/register")
    public ResponseEntity<Object> registerUser(@RequestBody @Valid RegistrationRequest request) {
        try {

            AuthenticationResponse response = userService.registerUser(request);
            eventPublisher.publishEvent(new OnRegistrationCompleteEvent(request));
            return ResponseEntity.ok(response);

        }catch (DuplicateIdException | DuplicatePhoneNumberException e) {
            return createErrorResponse(HttpStatus.SC_BAD_REQUEST, e.getMessage());
        } catch (Exception e) {
            return createErrorResponse(HttpStatus.SC_INTERNAL_SERVER_ERROR, "Unknown error");
        }

    }

    @PostMapping("/login")
    public ResponseEntity<Object> loginUser(@RequestBody @Valid AuthenticationRequest request){
        AuthenticationResponse response = userService.loginUser(request);
        return ResponseEntity.ok(response);
    }

    @GetMapping("/hello")
    public String hello(){
        return "HI";
    }

    private ResponseEntity<Object> createErrorResponse(int status, String message) {
        ErrorResponse errorResponse = ErrorResponse.builder()
                .status(status)
                .message(message)
                .build();

        return new ResponseEntity<>(errorResponse, HttpStatusCode.valueOf(status));
    }
}
