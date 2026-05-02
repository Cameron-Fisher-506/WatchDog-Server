package za.co.watchdog.features.clientManagement.presentation.model.clientOnboarding.dto;

public record AddressDto(
        String latitude,
        String longitude,
        String addressLineOne,
        String addressLineTwo,
        String suburb,
        String postalCode,
        Long clientId,
        Long zoneId
) {
}
