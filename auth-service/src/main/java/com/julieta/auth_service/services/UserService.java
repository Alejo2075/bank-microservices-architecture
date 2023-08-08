package com.julieta.auth_service.services;

import com.julieta.auth_service.dtos.RegistrationRequest;
import com.julieta.auth_service.dtos.RegistrationResponse;
import com.julieta.auth_service.entities.UserEntity;
import com.julieta.auth_service.exceptions.*;
import com.julieta.auth_service.models.User;

import java.util.List;
import java.util.Optional;

public interface UserService {

    RegistrationResponse registerUser(RegistrationRequest request) throws DuplicateIdException;

}
