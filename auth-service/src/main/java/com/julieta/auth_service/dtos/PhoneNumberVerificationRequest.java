package com.julieta.auth_service.dtos;

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
public class PhoneNumberVerificationRequest {

    @NotNull
    @NotEmpty
    private String userId;

    @NotNull
    @NotEmpty
    private String code;
}
