package za.co.watchdog.features.clientManagement.presentation.model.client.dto;

import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import lombok.Builder;

import java.time.Instant;

@Builder
public record UserDto(
        Long userId,
        String emailAddress,
        String password,
        @Enumerated(EnumType.STRING)
        UserRole userRole,
        Boolean isActive,
        Instant createdAt
) {

}
