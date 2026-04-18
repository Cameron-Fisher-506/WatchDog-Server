package za.co.watchdog.common.domain.model;

import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import lombok.Builder;
import lombok.Data;
import lombok.Getter;
import za.co.watchdog.common.domain.model.AccountStatus;

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
        private UserStatus userStatus;
        private Instant createdAt;
        private AccountStatus accountStatus;
        private String verificationCode;
        private Instant verificationCodeExpiresAt;

        public User copyWith(String passwordHash) {
                return User.builder()
                        .userId(this.userId)
                        .accountStatus(this.accountStatus)
                        .emailAddress(this.emailAddress)
                        .password(passwordHash)
                        .userRole(this.userRole)
                        .userStatus(this.userStatus)
                        .createdAt(this.createdAt)
                        .verificationCode(this.verificationCode)
                        .verificationCodeExpiresAt(this.verificationCodeExpiresAt)
                        .build();
        }
}
