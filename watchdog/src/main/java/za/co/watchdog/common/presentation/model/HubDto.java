package za.co.watchdog.common.presentation.model;

import java.time.Instant;

public record HubDto(
        String macAddress,
        String status,
        Instant lastHeartbeat

) {
}
