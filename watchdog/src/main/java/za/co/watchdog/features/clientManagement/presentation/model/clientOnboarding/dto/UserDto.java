package za.co.watchdog.features.clientManagement.presentation.model.clientOnboarding.dto;

import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

import java.time.Instant;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@SuperBuilder
public abstract class UserDto {
    private Long userId;
    private String emailAddress;
    private String password;
    @Enumerated(EnumType.STRING)
    private UserRole userRole;
    private Boolean isActive;
    private Instant createdAt;
}
