package br.com.neomed.api.domain.auth;

public record UserAuthResponseDTO(
        Long id,
        String login,
        String token
) {
}