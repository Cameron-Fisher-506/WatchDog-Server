package za.co.watchdog.common.domain.model;

import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import lombok.Builder;
import lombok.Data;
import lombok.Getter;

import java.time.Instant;

@Data
@Getter
@Builder
public class User {
        private Long userId;
        private String emailAddress;
        private String password;
        @Enumerated(EnumType.STRING)
        private UserRole userRole;
        private Boolean isActive;
        private Instant createdAt;

        public User copyWith(String passwordHash) {
                return User.builder()
                        .userId(this.userId)
                        .emailAddress(this.emailAddress)
                        .password(passwordHash)
                        .userRole(this.userRole)
                        .isActive(this.isActive)
                        .createdAt(this.createdAt)
                        .build();
        }
}
