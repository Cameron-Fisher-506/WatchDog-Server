package za.co.watchdog.features.incidentManagement.presentation.model.incident.dto;

import lombok.Builder;

@Builder
public record PatrolDto(
        String officerCode,
        String name,
        String surname,
        String contactNumber
) {

}
