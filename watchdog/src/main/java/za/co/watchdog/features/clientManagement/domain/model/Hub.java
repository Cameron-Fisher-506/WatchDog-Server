package za.co.watchdog.features.clientManagement.domain.model;

import java.time.Instant;

public record Hub(
        Long hubId,
        Sensor sensor,
        String macAddress,
        String status,
        Instant lastHeartbeat
) {
}
