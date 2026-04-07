package za.co.watchdog.common.data.local.database.model;

import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.SuperBuilder;

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
    private UserStatus userStatus;
    private Instant createdAt;
    private String verificationCode;
    private Instant verificationCodeExpiresAt;
}
