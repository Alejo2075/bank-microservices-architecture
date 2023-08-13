package com.julieta.auth_service.service;

import com.julieta.auth_service.dto.AuthenticationRequest;
import com.julieta.auth_service.dto.AuthenticationResponse;
import com.julieta.auth_service.dto.RegistrationRequest;
import com.julieta.auth_service.exception.DuplicateEmailException;
import com.julieta.auth_service.exception.DuplicateIdException;
import com.julieta.auth_service.exception.DuplicatePhoneNumberException;
import com.julieta.auth_service.jwt.JwtTokenProvider;
import com.julieta.auth_service.model.User;
import com.julieta.auth_service.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import static com.julieta.auth_service.util.UserConverter.*;

@Service
@RequiredArgsConstructor
public class AuthServiceImpl implements AuthService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtTokenProvider tokenProvider;
    private final AuthenticationManager authenticationManager;

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
        String jwtToken = tokenProvider.createToken(user.getPhoneNumber());

        return AuthenticationResponse.builder()
                .accessToken(jwtToken)
                .build();
    }

    @Override
    public AuthenticationResponse loginUser(AuthenticationRequest request) {
        User user = convertFromAuthenticationRequestToUser(request);

        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        user.getPhoneNumber(),
                        user.getPassword()
                )
        );

        userRepository.findByPhoneNumber(user.getPhoneNumber());

        String jwtToken = tokenProvider.createToken(user.getPhoneNumber());

        return AuthenticationResponse.builder()
                .accessToken(jwtToken)
                .build();
    }


}
