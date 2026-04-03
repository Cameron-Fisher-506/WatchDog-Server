package za.co.watchdog.features.authManagement.presentation.model.client.dto;

public record LocationDto(
        Long locationId,
        String latitude,
        String longitude
) {
}
