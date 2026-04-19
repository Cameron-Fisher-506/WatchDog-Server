package za.co.watchdog.common.presentation.model;

public record AddressDto(
        String latitude,
        String longitude,
        String addressLineOne,
        String addressLineTwo,
        String suburb,
        String postalCode
) {
}
