package com.julieta.auth_service.service;

import com.julieta.auth_service.dto.AuthenticationRequest;
import com.julieta.auth_service.dto.AuthenticationResponse;
import com.julieta.auth_service.dto.RegistrationRequest;
import com.julieta.auth_service.exception.DuplicateIdException;
import com.julieta.auth_service.exception.DuplicatePhoneNumberException;

public interface UserService {

    AuthenticationResponse registerUser(RegistrationRequest request) throws DuplicateIdException, DuplicatePhoneNumberException;

    AuthenticationResponse loginUser(AuthenticationRequest request);
}
