package za.co.watchdog.features.authManagement.presentation.model.register;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import za.co.watchdog.common.presentation.model.UserStatus;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Builder
public class RegisterResponseDto {
    Long userId;
    String deviceFingerprint;
    UserStatus status;
    String token;
    Boolean isOtpSent;
    String message;
}
