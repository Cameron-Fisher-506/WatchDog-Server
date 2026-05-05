package za.co.watchdog.features.clientManagement.presentation.model.clientOnboarding.dto;

import java.time.Instant;

public record HubDto(
        String macAddress,
        String status,
        Instant lastHeartbeat
) {
}
