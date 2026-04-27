package za.co.watchdog.common.presentation.model;

public record AddressDto(
        String addressLineOne,
        String addressLineTwo,
        String suburb,
        String postalCode
) {
}
