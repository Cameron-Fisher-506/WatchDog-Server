package za.co.watchdog.common.presentation.model;

public record SensorDto(
        Long sensorId,
        String type,
        String zoneName
) {
}
