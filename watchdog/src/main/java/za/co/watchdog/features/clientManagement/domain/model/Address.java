package za.co.watchdog.features.clientManagement.domain.model;

import lombok.Builder;

@Builder
public record Address(
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
