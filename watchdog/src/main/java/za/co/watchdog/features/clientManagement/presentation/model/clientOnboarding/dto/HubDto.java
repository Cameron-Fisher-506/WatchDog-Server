package za.co.watchdog.features.clientManagement.presentation.model.clientOnboarding.dto;

import java.time.Instant;

public record HubDto(
        SensorDto sensorDto,
        String macAddress,
        String status,
        Instant lastHeartbeat
) {
}
