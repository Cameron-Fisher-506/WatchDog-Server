package za.co.watchdog.features.authManagement.domain.model;

import lombok.Builder;

import java.time.Instant;

@Builder
public record AuthenticatedUser(
        Long userId,
        String emailAddress,
        String token,
        UserRole userRole,
        Instant authenticatedAt
) {
}
