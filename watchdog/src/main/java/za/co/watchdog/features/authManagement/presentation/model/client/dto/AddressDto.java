package za.co.watchdog.features.authManagement.presentation.model.client.dto;

public record AddressDto(
        String latitude,
        String longitude,
        String addressLineOne,
        String addressLineTwo,
        String suburb,
        String postalCode
) {
}
