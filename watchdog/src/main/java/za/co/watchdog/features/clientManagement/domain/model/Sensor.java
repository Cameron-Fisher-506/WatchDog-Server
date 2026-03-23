package za.co.watchdog.features.clientManagement.domain.model;

public record Sensor(
        Long sensorId,
        String type,
        String zoneName
) {
}
