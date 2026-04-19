package za.co.watchdog.common.domain.model;

import lombok.Builder;

import java.time.Instant;

@Builder
public record Hub(
        Long hubId,
        String macAddress,
        String status,
        Instant lastHeartbeat,
        Long clientId,
        Long locationId
) {
}
