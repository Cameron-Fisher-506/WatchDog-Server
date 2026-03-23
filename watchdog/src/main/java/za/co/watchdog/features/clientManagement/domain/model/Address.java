package za.co.watchdog.features.clientManagement.domain.model;

public record Address(
        Long addressId,
        String latitude,
        String longitude,
        String addressLineOne,
        String addressLineTwo,
        String suburb,
        String postalCode
) {
}
