package za.co.watchdog.features.clientManagement.presentation.model;

public record SensorDto(
        Long sensorId,
        String type,
        String zoneName
) {
}
