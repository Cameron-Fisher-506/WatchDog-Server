package za.co.watchdog.features.authManagement.presentation.model.auth;

import za.co.watchdog.features.authManagement.domain.model.UserRole;

import java.time.Instant;

public record AuthResponseDto(
        Long userId,
        String emailAddress,
        String token,
        UserRole userRole,
        Instant authenticatedAt
) {
}
