package br.com.neomed.api.user.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public record CreateUserRequest(
        Long personId,

        @NotBlank
        String login,

        @NotBlank
        @Size(min = 6)
        String password,

        String role
) {
}