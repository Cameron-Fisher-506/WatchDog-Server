package za.co.watchdog.features.authManagement.presentation.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import za.co.watchdog.common.presentation.model.UserStatus;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Builder
public class AuthResponseDto {
    Long userId;
    String deviceFingerprint;
    UserStatus status;
    String token;
    String message;
}
