package com.julieta.auth_service.util;

import com.julieta.auth_service.dto.ErrorResponse;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;

public class ResponseUtils {

    public static ResponseEntity<Object> createErrorResponse(int status, String message) {
        ErrorResponse errorResponse = ErrorResponse.builder()
                .status(status)
                .message(message)
                .build();

        return new ResponseEntity<>(errorResponse, HttpStatusCode.valueOf(status));
    }
}
