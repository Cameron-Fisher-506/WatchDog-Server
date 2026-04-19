package za.co.watchdog.features.authManagement.presentation.model.otp;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Builder
public class ValidateOneTimePinRequestDto {
    private Long userId;
    private String oneTimePin;
}
