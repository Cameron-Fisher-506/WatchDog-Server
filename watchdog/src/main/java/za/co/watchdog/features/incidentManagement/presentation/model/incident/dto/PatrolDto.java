package za.co.watchdog.features.incidentManagement.presentation.model.incident.dto;

import lombok.Builder;

@Builder
public record PatrolDto(
        Long patrolId,
        String officerCode,
        String name,
        String surname,
        String contactNumber
) {

}
