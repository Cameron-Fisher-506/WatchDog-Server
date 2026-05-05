package za.co.watchdog.features.incidentManagement.presentation.model.incident.dto;

import lombok.Builder;

@Builder
public record SecurityCompanyDto(
     Long securityCompanyId,
     String name,
     String contactNumber
) {
}
