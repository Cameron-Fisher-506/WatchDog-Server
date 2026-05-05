package za.co.watchdog.features.clientManagement.presentation.model.clientOnboarding.dto;

public record LocationDto(
        String latitude,
        String longitude,
        AddressDto addressDto,
        Long zoneId
) {
}
