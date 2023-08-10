package com.julieta.auth_service.dto;

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
public class AuthenticationRequest {

    @NotNull
    @NotEmpty
    @ValidPhoneNumber
    private String phoneNumber;

    @NotNull
    @NotEmpty
    @ValidPassword
    private String password;
}
