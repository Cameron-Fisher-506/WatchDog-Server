package za.co.watchdog.features.authManagement.presentation.model;

import za.co.watchdog.common.domain.model.UserRole;

import java.time.Instant;

public record AuthResponseDto(
        Long userId,
        String emailAddress,
        String token,
        UserRole userRole,
        Instant authenticatedAt
) {
}
