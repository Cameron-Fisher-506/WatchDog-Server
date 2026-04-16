package za.co.watchdog.common.presentation.model;

import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import lombok.*;

import java.time.Instant;


@NoArgsConstructor
@AllArgsConstructor
@Data
public class UserDto {
    private Long userId;
    private String emailAddress;
    private String password;
    @Enumerated(EnumType.STRING)
    private UserRole userRole;
    private UserStatus userStatus;
    private Boolean isActive;
    private Instant createdAt;
}
