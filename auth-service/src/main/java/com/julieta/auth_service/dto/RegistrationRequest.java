package com.julieta.auth_service.dto;

import com.julieta.auth_service.annotation.ValidEmail;
import com.julieta.auth_service.annotation.ValidId;
import com.julieta.auth_service.annotation.ValidPassword;
import com.julieta.auth_service.annotation.ValidPhoneNumber;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class RegistrationRequest {

    @NotNull
    @NotEmpty
    @ValidId
    private String id;

    @NotNull
    @NotEmpty
    private String firstName;

    @NotNull
    @NotEmpty
    private String lastName;

    @NotNull
    @NotEmpty
    @ValidEmail
    private String email;

    @NotNull
    @NotEmpty
    @ValidPhoneNumber
    private String phoneNumber;

    @NotNull
    @NotEmpty
    @ValidPassword
    private String password;
}
