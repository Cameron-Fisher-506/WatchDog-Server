package za.co.watchdog.features.authManagement.presentation.model.client.dto;

import java.time.Instant;

public record HubDto(
        Long hubId,
        SensorDto sensorDto,
        String macAddress,
        String status,
        Instant lastHeartbeat
) {
}
