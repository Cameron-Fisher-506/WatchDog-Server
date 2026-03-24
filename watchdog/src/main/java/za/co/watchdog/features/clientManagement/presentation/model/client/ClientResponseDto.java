package za.co.watchdog.features.clientManagement.presentation.model.client;

import za.co.watchdog.features.clientManagement.presentation.model.client.dto.AddressDto;
import za.co.watchdog.features.clientManagement.presentation.model.client.dto.HubDto;
import za.co.watchdog.features.clientManagement.presentation.model.client.dto.LocationDto;

public record ClientResponseDto(
        Long clientId,
        HubDto hubDto,
        String name,
        String surname,
        String contactNumber,
        String emailAddress,
        AddressDto addressDto,
        LocationDto locationDto
) {
}
