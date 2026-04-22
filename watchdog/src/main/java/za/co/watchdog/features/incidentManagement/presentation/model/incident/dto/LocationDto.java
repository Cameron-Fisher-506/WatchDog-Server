package za.co.watchdog.features.incidentManagement.presentation.model.incident.dto;

import lombok.Builder;

@Builder
public record LocationDto(
        String latitude,
        String longitude
) {
}
