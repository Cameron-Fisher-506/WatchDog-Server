package za.co.watchdog.features.clientManagement.presentation.model.clientOnboarding;

import lombok.*;
import za.co.watchdog.features.clientManagement.presentation.model.clientOnboarding.dto.AddressDto;
import za.co.watchdog.features.clientManagement.presentation.model.clientOnboarding.dto.HubDto;
import za.co.watchdog.features.clientManagement.presentation.model.clientOnboarding.dto.LocationDto;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Builder
public class ClientOnboardingRequestDto {
    HubDto hubDto;
    String name;
    String surname;
    String contactNumber;
    AddressDto addressDto;
    LocationDto locationDto;
}