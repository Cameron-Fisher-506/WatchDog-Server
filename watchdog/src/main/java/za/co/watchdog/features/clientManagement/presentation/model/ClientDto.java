package za.co.watchdog.features.clientManagement.presentation.model;

import za.co.watchdog.features.clientManagement.domain.model.Address;
import za.co.watchdog.features.clientManagement.domain.model.Hub;
import za.co.watchdog.features.clientManagement.domain.model.Location;

public record ClientDto(
        Long clientId,
        HubDto hubDto,
        String name,
        String surname,
        String contactNumber,
        String emailAddress,
        String passwordHash,
        AddressDto addressDto,
        LocationDto locationDto
) {
}
