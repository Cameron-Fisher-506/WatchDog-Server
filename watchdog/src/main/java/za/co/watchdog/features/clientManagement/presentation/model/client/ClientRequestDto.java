package za.co.watchdog.features.clientManagement.presentation.model.client;

import za.co.watchdog.features.clientManagement.presentation.model.client.dto.AddressDto;
import za.co.watchdog.features.clientManagement.presentation.model.client.dto.HubDto;
import za.co.watchdog.features.clientManagement.presentation.model.client.dto.LocationDto;

public record ClientRequestDto(
        Long clientId,
        HubDto hubDto,
        String name,
        String surname,
        String contactNumber,
        String emailAddress,
        String password,
        AddressDto addressDto,
        LocationDto locationDto
) {
}
