package za.co.watchdog.features.clientManagement.presentation.model.client;

import za.co.watchdog.features.clientManagement.presentation.model.client.dto.AddressDto;
import za.co.watchdog.features.clientManagement.presentation.model.client.dto.HubDto;
import za.co.watchdog.features.clientManagement.presentation.model.client.dto.LocationDto;
import za.co.watchdog.features.clientManagement.presentation.model.client.dto.UserDto;

public record ClientResponseDto(
        Long clientId,
        String token
) {
}
