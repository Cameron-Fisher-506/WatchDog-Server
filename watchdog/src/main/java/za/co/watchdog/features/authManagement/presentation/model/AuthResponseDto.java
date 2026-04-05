package za.co.watchdog.features.authManagement.presentation.model;

import java.time.Instant;

public record AuthResponseDto(
        Long userId,
        String emailAddress,
        String token,
        UserRole userRole,
        Instant authenticatedAt
) {
}
