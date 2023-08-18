package com.julieta.auth_service.service;

import com.julieta.auth_service.dto.AuthenticationRequest;
import com.julieta.auth_service.dto.AuthenticationResponse;
import com.julieta.auth_service.dto.RegistrationRequest;
import com.julieta.auth_service.exception.DuplicateEmailException;
import com.julieta.auth_service.exception.DuplicateIdException;
import com.julieta.auth_service.exception.DuplicatePhoneNumberException;

import javax.security.sasl.AuthenticationException;

public interface AuthService {

    AuthenticationResponse registerUser(RegistrationRequest request) throws DuplicateIdException, DuplicatePhoneNumberException, DuplicateEmailException;

    AuthenticationResponse loginUser(AuthenticationRequest request) throws AuthenticationException;
}
