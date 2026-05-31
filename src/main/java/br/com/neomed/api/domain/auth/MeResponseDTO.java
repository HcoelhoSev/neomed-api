package br.com.neomed.api.domain.auth;

import java.util.List;

public record MeResponseDTO(
        Long id,
        String login,
        List<String> authorities
) {
}