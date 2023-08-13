package com.julieta.auth_service.util;

import com.julieta.auth_service.dto.AuthenticationRequest;
import com.julieta.auth_service.dto.RegistrationRequest;
import com.julieta.auth_service.entity.UserEntity;
import com.julieta.auth_service.model.User;

public class UserConverter {

    public static UserEntity convertFromUserToUserEntity(User user){
        return UserEntity.builder()
                .id(user.getId())
                .firstName(capitalizeFirstLetter(user.getFirstName()))
                .lastName(capitalizeFirstLetter(user.getLastName()))
                .email(user.getEmail())
                .phoneNumber(user.getPhoneNumber())
                .password(user.getPassword())
                .build();
    }

    public static User convertFromRegistrationRequestToUser(RegistrationRequest request){
        return User.builder()
                .id(request.getId())
                .firstName(request.getFirstName())
                .lastName(request.getLastName())
                .email(request.getEmail())
                .phoneNumber(request.getPhoneNumber())
                .password(request.getPassword())
                .build();
    }

    public static User convertFromAuthenticationRequestToUser(AuthenticationRequest request){
        return User.builder()
                .phoneNumber(request.getPhoneNumber())
                .password(request.getPassword())
                .build();
    }

    private static String capitalizeFirstLetter(String input) {
        if (input == null || input.trim().isEmpty()) {
            return input;
        }

        String[] words = input.split("\\s+");
        StringBuilder result = new StringBuilder();

        for (String word : words) {
            if (word.length() > 0) {
                result.append(word.substring(0, 1).toUpperCase());
                result.append(word.substring(1).toLowerCase());
                result.append(" ");
            }
        }

        return result.toString().trim();
    }
}
