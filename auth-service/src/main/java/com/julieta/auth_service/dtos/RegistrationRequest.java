package com.julieta.auth_service.dtos;

import com.julieta.auth_service.annotations.ValidId;
import com.julieta.auth_service.annotations.ValidPassword;
import com.julieta.auth_service.annotations.ValidPhoneNumber;
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
    @ValidPhoneNumber
    private String phoneNumber;

    @NotNull
    @NotEmpty
    @ValidPassword
    private String password;
}
