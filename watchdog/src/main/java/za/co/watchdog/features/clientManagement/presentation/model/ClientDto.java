package za.co.watchdog.features.clientManagement.presentation.model;

public record ClientDto(
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
