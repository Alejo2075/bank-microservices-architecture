package com.julieta.auth_service.controller;

import com.julieta.auth_service.dto.AuthenticationRequest;
import com.julieta.auth_service.dto.AuthenticationResponse;
import com.julieta.auth_service.dto.RegistrationRequest;
import com.julieta.auth_service.exception.DuplicateEmailException;
import com.julieta.auth_service.exception.DuplicateIdException;
import com.julieta.auth_service.exception.DuplicatePhoneNumberException;
import com.julieta.auth_service.service.AuthService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.apache.http.HttpStatus;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import static com.julieta.auth_service.util.ResponseUtils.createErrorResponse;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/auth")
public class AuthController {

    private final AuthService userService;

    @PostMapping("/register")
    public ResponseEntity<Object> registerUser(@RequestBody @Valid RegistrationRequest request) {
        try {

            AuthenticationResponse response = userService.registerUser(request);
            //eventPublisher.publishEvent(new OnRegistrationCompleteEvent(request));
            return ResponseEntity.ok(response);

        }catch (DuplicateIdException | DuplicatePhoneNumberException | DuplicateEmailException e) {
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

}
