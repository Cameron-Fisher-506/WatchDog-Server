package za.co.watchdog.features.authManagement.presentation.model.client.dto;

public record SensorDto(
        Long sensorId,
        String type,
        String zoneName
) {
}
