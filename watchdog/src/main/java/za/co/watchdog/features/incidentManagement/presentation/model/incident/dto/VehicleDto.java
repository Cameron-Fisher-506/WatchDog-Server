package za.co.watchdog.features.incidentManagement.presentation.model.incident.dto;

import lombok.Builder;

@Builder
public record VehicleDto(
        Long vehicleId,
        String plateNumber,
        String model
) {
}
