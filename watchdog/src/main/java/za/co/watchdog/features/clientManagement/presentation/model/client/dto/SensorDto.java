package za.co.watchdog.features.clientManagement.presentation.model.client.dto;

public record SensorDto(
        Long sensorId,
        String type,
        String zoneName
) {
}
