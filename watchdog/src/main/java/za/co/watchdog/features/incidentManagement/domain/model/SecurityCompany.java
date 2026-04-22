package za.co.watchdog.features.incidentManagement.domain.model;

import lombok.Builder;

@Builder
public record SecurityCompany(
        Long securityCompanyId,
        String name,
        String psiraLicense,
        String contactNumber
) {
}
