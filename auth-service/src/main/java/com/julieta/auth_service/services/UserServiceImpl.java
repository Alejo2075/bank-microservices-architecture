package com.julieta.auth_service.services;

import com.julieta.auth_service.dtos.RegistrationRequest;
import com.julieta.auth_service.dtos.RegistrationResponse;
import com.julieta.auth_service.entities.UserEntity;
import com.julieta.auth_service.exceptions.*;
import com.julieta.auth_service.models.User;
import com.julieta.auth_service.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import static com.julieta.auth_service.utils.UserConverter.*;

@Service
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    @Autowired
    public UserServiceImpl(UserRepository userRepository, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public RegistrationResponse registerUser(RegistrationRequest request) throws DuplicateIdException{
        User user = convertFromRegistrationRequestToUser(request);

        if (userRepository.findById(user.getId()).isPresent()) {
            throw new DuplicateIdException("An user with this identification number already exists");
        }

        user.setPassword(passwordEncoder.encode(user.getPassword()));

        UserEntity entityResponse = userRepository.save(convertFromUserToUserEntity(user));
        User response = convertFromUserEntityToUser(entityResponse);

        return convertFromUserToRegistrationResponse(response);
    }



}
