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
        public User copyWith(String passwordHash) {
                return new User(this.userId, this.emailAddress, passwordHash, this.userRole, this.isActive, this.createdAt);
        }
}
