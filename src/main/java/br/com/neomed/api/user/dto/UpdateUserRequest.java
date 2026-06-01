package br.com.neomed.api.user.dto;

public record UpdateUserRequest(
        Long personId,
        Boolean active
) {
}