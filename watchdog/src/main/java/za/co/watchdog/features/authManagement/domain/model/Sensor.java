package za.co.watchdog.features.authManagement.domain.model;

import lombok.Builder;

@Builder
public record Sensor(
        Long sensorId,
        String type,
        String zoneName
) {
}
