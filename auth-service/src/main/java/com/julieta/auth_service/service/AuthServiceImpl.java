package com.julieta.auth_service.service;

import com.julieta.auth_service.dto.AuthenticationRequest;
import com.julieta.auth_service.dto.AuthenticationResponse;
import com.julieta.auth_service.dto.RegistrationRequest;
import com.julieta.auth_service.entity.UserEntity;
import com.julieta.auth_service.exception.DuplicateEmailException;
import com.julieta.auth_service.exception.DuplicateIdException;
import com.julieta.auth_service.exception.DuplicatePhoneNumberException;
import com.julieta.auth_service.jwt.JwtTokenProvider;
import com.julieta.auth_service.model.User;
import com.julieta.auth_service.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import javax.security.sasl.AuthenticationException;
import java.util.Optional;

import static com.julieta.auth_service.util.UserConverter.*;

@Service
@RequiredArgsConstructor
public class AuthServiceImpl implements AuthService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtTokenProvider tokenProvider;

    @Override
    public AuthenticationResponse registerUser(RegistrationRequest request) throws DuplicateIdException, DuplicatePhoneNumberException, DuplicateEmailException {
        User user = convertFromRegistrationRequestToUser(request);

        if (userRepository.findById(user.getId()).isPresent()) {
            throw new DuplicateIdException("An user with this identification number already exists");
        } else if (userRepository.findByEmail(user.getEmail()).isPresent()) {
            throw new DuplicateEmailException("An user with this email already exists");
        } else if(userRepository.findByPhoneNumber(user.getPhoneNumber()).isPresent()){
            throw new DuplicatePhoneNumberException("An user with this phone number already exists");
        }

        user.setPassword(passwordEncoder.encode(user.getPassword()));

        userRepository.save(convertFromUserToUserEntity(user));
        String jwtToken = tokenProvider.generateToken(user.getPhoneNumber());

        return AuthenticationResponse.builder()
                .accessToken(jwtToken)
                .build();
    }

    @Override
    public AuthenticationResponse loginUser(AuthenticationRequest request) throws AuthenticationException {
        User user = convertFromAuthenticationRequestToUser(request);

        Optional<UserEntity> optionalUserEntity = userRepository.findByPhoneNumber(user.getPhoneNumber());
        if(optionalUserEntity.isEmpty()){
            throw new AuthenticationException("User with this phone number doesn't exist");
        }

        UserEntity savedUserEntity = optionalUserEntity.get();

        if (!passwordEncoder.matches(user.getPassword(), savedUserEntity.getPassword())) {
            throw new AuthenticationException("Invalid password");
        }

        String jwtToken = tokenProvider.generateToken(user.getPhoneNumber());

        return AuthenticationResponse.builder()
                .accessToken(jwtToken)
                .build();
    }


}
