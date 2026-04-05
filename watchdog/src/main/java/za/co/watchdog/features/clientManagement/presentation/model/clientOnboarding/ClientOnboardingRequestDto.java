package za.co.watchdog.features.clientManagement.presentation.model.clientOnboarding;

import lombok.*;
import za.co.watchdog.features.clientManagement.presentation.model.clientOnboarding.dto.AddressDto;
import za.co.watchdog.features.clientManagement.presentation.model.clientOnboarding.dto.HubDto;
import za.co.watchdog.features.clientManagement.presentation.model.clientOnboarding.dto.LocationDto;
import za.co.watchdog.features.clientManagement.presentation.model.clientOnboarding.dto.UserDto;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Builder
public class ClientOnboardingRequestDto {
    Long clientId;
    HubDto hubDto;
    String name;
    String surname;
    String contactNumber;
    AddressDto addressDto;
    LocationDto locationDto;
    UserDto userDto;
}