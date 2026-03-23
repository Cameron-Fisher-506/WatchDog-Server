package za.co.watchdog.features.clientManagement.domain.model;

public record Location(
        Long locationId,
        String latitude,
        String longitude
) {
}
