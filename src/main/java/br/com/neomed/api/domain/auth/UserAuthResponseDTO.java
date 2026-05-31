package br.com.neomed.api.domain.auth;

import java.util.Set;

public record UserAuthResponseDTO(
        Long id,
        String name,
        String email,
        String profile,
        Set<String> authorities
) {}