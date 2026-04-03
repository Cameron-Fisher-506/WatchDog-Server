package za.co.watchdog.features.authManagement.domain.model;

public record Sensor(
        Long sensorId,
        String type,
        String zoneName
) {
}
