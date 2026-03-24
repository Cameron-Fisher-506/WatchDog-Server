package za.co.watchdog.features.clientManagement.presentation.model.client.dto;

public record LocationDto(
        Long locationId,
        String latitude,
        String longitude
) {
}
