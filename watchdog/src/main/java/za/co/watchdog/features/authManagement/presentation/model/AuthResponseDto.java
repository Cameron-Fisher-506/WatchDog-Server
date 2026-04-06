package za.co.watchdog.features.authManagement.presentation.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import za.co.watchdog.common.domain.model.UserRole;

import java.time.Instant;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Builder
public class AuthResponseDto {
    Long userId;
    String emailAddress;
    String token;
    UserRole userRole;
    Instant authenticatedAt;
}
