package za.co.watchdog.features.clientManagement.presentation.model;

import java.time.Instant;

public record HubDto(
        Long hubId,
        SensorDto sensorDto,
        String macAddress,
        String status,
        Instant lastHeartbeat
) {
}
