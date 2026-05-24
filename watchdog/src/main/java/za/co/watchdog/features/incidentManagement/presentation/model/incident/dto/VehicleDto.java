package za.co.watchdog.features.incidentManagement.presentation.model.incident.dto;

import lombok.Builder;

@Builder
public record VehicleDto(
        String plateNumber,
        String model
) {
}
