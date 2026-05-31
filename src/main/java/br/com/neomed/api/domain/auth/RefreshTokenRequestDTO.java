package br.com.neomed.api.domain.auth;

import jakarta.validation.constraints.NotBlank;

public record RefreshTokenRequestDTO(
        @NotBlank(message = "Refresh token é obrigatório")
        String refreshToken
) {}
