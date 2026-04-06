package za.co.watchdog.features.clientManagement.presentation.model.clientOnboarding.dto;

import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import lombok.*;
import za.co.watchdog.common.presentation.model.UserRole;

import java.time.Instant;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Builder
public class UserDto {
    private Long userId;
    private String emailAddress;
    private String password;
    @Enumerated(EnumType.STRING)
    private UserRole userRole;
    private Boolean isActive;
    private Instant createdAt;
}
