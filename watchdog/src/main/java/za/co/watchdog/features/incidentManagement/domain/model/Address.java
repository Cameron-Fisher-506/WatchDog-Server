package za.co.watchdog.features.incidentManagement.domain.model;

import lombok.Builder;

@Builder
public record Address(
        Long addressId,
        String latitude,
        String longitude,
        String addressLineOne,
        String addressLineTwo,
        String suburb,
        String postalCode,
        Long clientId,
        Long zoneId
) {
}
