package za.co.watchdog.features.clientManagement.presentation.model.clientOnboarding;

import lombok.*;
import za.co.watchdog.common.presentation.model.AddressDto;
import za.co.watchdog.common.presentation.model.HubDto;
import za.co.watchdog.common.presentation.model.LocationDto;
import za.co.watchdog.common.presentation.model.UserDto;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Builder
public class ClientOnboardingRequestDto {
    HubDto hubDto;
    String name;
    String surname;
    String contactNumber;
    LocationDto locationDto;
    Long userId;
}