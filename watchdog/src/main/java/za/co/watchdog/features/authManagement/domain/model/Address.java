package za.co.watchdog.features.authManagement.domain.model;

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
