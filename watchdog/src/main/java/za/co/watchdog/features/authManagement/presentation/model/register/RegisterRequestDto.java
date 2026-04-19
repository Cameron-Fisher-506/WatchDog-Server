package za.co.watchdog.features.authManagement.presentation.model.register;

import lombok.*;
import za.co.watchdog.common.presentation.model.UserDto;
import za.co.watchdog.features.authManagement.presentation.model.register.dto.DeviceDto;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Builder
public class RegisterRequestDto extends UserDto {
    private DeviceDto deviceDto;
}
