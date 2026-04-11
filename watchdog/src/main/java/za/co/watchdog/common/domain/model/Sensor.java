package za.co.watchdog.common.domain.model;

import lombok.Builder;

@Builder
public record Sensor(
        Long sensorId,
        String type,
        String zoneName,
        Long hubId
) {
}
