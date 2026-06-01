package br.com.neomed.api.user.dto;

import java.time.LocalDateTime;

public record UserResponse(
        Long id,
        Long personId,
        String login,
        Boolean active,
        Integer loginAttempts,
        LocalDateTime lastLogin,
        LocalDateTime createdAt,
        LocalDateTime updatedAt
) {
}