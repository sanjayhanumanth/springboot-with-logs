package com.demo.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

public class AuthDto {

    @Data
    @Builder
    @NoArgsConstructor
    @AllArgsConstructor
    @Schema(description = "Register Request")
    public static class RegisterRequest {

        @NotBlank(message = "Username is required")
        @Size(min = 3, max = 50)
        @Schema(description = "Username", example = "john_doe")
        private String username;

        @NotBlank(message = "Email is required")
        @Email(message = "Invalid email format")
        @Schema(description = "Email address", example = "john@example.com")
        private String email;

        @NotBlank(message = "Password is required")
        @Size(min = 6, message = "Password must be at least 6 characters")
        @Schema(description = "Password", example = "secret123")
        private String password;
    }

    @Data
    @Builder
    @NoArgsConstructor
    @AllArgsConstructor
    @Schema(description = "Login Request")
    public static class LoginRequest {

        @NotBlank(message = "Username is required")
        @Schema(description = "Username", example = "john_doe")
        private String username;

        @NotBlank(message = "Password is required")
        @Schema(description = "Password", example = "secret123")
        private String password;
    }

    @Data
    @Builder
    @NoArgsConstructor
    @AllArgsConstructor
    @Schema(description = "JWT Auth Response")
    public static class AuthResponse {

        @Schema(description = "JWT token")
        private String token;

        @Schema(description = "Token type", example = "Bearer")
        private String tokenType = "Bearer";

        @Schema(description = "Username")
        private String username;

        @Schema(description = "Role")
        private String role;
    }
}
