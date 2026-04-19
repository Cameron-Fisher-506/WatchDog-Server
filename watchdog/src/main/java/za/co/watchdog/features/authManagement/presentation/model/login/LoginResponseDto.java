package za.co.watchdog.features.authManagement.presentation.model.login;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import za.co.watchdog.common.presentation.model.UserStatus;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Builder
public class LoginResponseDto {
    Long userId;
    Boolean isOtpRequired;
    UserStatus status;
    String token;
    String message;
}
