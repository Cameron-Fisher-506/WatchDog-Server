package za.co.watchdog.common.data.local.database.model;

import jakarta.persistence.*;
import lombok.*;
import za.co.watchdog.common.domain.model.AccountStatus;
import za.co.watchdog.common.domain.model.UserRole;
import za.co.watchdog.common.domain.model.UserStatus;

import java.time.Instant;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
@Table(name = "app_user")
public class UserEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long userId;
    private String emailAddress;
    private String passwordHash;
    @Enumerated(EnumType.STRING)
    private UserRole userRole;
    @Enumerated(EnumType.STRING)
    private UserStatus userStatus;
    private Instant createdAt;
    @Enumerated(EnumType.STRING)
    private AccountStatus accountStatus;
    private String verificationCode;
    private Instant verificationCodeExpiresAt;
}
