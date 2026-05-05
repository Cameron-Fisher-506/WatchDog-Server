package za.co.watchdog.common.presentation.model;

import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import lombok.*;
import za.co.watchdog.common.domain.model.UserRole;

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
    private Boolean isActive;
    private Instant createdAt;
}
