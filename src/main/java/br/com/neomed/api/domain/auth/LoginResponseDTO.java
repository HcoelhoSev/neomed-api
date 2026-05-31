package br.com.neomed.api.domain.auth;

import java.util.Set;

public record LoginResponseDTO(
        String accessToken,
        String refreshToken,
        Long expiresIn,
        UserAuthResponseDTO user
) {}