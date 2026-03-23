package za.co.watchdog.features.clientManagement.presentation.model;

public record LocationDto(
        Long locationId,
        String latitude,
        String longitude
) {
}
