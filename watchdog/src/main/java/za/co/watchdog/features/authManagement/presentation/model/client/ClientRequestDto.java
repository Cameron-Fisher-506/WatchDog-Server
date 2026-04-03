package za.co.watchdog.features.authManagement.presentation.model.client;

import za.co.watchdog.features.authManagement.presentation.model.client.dto.AddressDto;
import za.co.watchdog.features.authManagement.presentation.model.client.dto.HubDto;
import za.co.watchdog.features.authManagement.presentation.model.client.dto.LocationDto;
import za.co.watchdog.features.authManagement.presentation.model.client.dto.UserDto;

public record ClientRequestDto(
        Long clientId,
        HubDto hubDto,
        String name,
        String surname,
        String contactNumber,
        AddressDto addressDto,
        LocationDto locationDto,
        UserDto userDto
) {
}
