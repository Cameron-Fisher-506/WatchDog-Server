package za.co.watchdog.features.clientManagement.domain.model;

import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;

import java.time.Instant;

public record User(
        Long userId,
        String emailAddress,
        String password,
        @Enumerated(EnumType.STRING)
        UserRole userRole,
        Boolean isActive,
        Instant createdAt
) {

}
