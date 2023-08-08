package com.julieta.auth_service.utils;

import com.julieta.auth_service.dtos.RegistrationRequest;
import com.julieta.auth_service.dtos.RegistrationResponse;
import com.julieta.auth_service.entities.UserEntity;
import com.julieta.auth_service.models.User;
import org.apache.http.HttpStatus;

public class UserConverter {

    public static UserEntity convertFromUserToUserEntity(User user){
        return UserEntity.builder()
                .id(user.getId())
                .firstName(capitalizeFirstLetter(user.getFirstName()))
                .lastName(capitalizeFirstLetter(user.getLastName()))
                .phoneNumber(user.getPhoneNumber())
                .password(user.getPassword())
                .build();
    }

    public static User convertFromUserEntityToUser(UserEntity userEntity){
        return User.builder()
                .id(userEntity.getId())
                .firstName(userEntity.getFirstName())
                .lastName(userEntity.getLastName())
                .phoneNumber(userEntity.getPhoneNumber())
                .password(userEntity.getPassword())
                .build();
    }

    public static User convertFromRegistrationRequestToUser(RegistrationRequest request){
        return User.builder()
                .id(request.getId())
                .firstName(request.getFirstName())
                .lastName(request.getLastName())
                .phoneNumber(request.getPhoneNumber())
                .password(request.getPassword())
                .build();
    }

    public static RegistrationResponse convertFromUserToRegistrationResponse(User user){
        return RegistrationResponse.builder()
                .status(HttpStatus.SC_CREATED)
                .firstName(user.getFirstName())
                .lastName(user.getLastName())
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
