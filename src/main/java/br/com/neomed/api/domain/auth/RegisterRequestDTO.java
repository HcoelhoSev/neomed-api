package br.com.neomed.api.domain.auth;

import jakarta.validation.constraints.NotBlank;

public record RegisterRequestDTO(
        @NotBlank String login,
        @NotBlank String password,
        String role
) {
}