package za.co.watchdog.features.authManagement.domain.model;

public record Location(
        Long locationId,
        String latitude,
        String longitude
) {
}
