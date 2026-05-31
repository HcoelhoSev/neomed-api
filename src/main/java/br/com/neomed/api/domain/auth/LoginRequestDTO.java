package br.com.neomed.api.domain.auth;

public record LoginRequestDTO(
        String login,
        String password
) {
}