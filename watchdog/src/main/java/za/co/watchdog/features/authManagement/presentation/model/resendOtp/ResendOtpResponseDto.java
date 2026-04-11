package za.co.watchdog.features.authManagement.presentation.model.resendOtp;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Builder
public class ResendOtpResponseDto {
    private Boolean isOtpSent;
}
