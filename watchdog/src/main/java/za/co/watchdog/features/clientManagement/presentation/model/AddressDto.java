package za.co.watchdog.features.clientManagement.presentation.model;

public record AddressDto(
        Long addressId,
        String latitude,
        String longitude,
        String addressLineOne,
        String addressLineTwo,
        String suburb,
        String postalCode
) {
}
