package za.co.watchdog.features.authManagement.domain.model;

import lombok.Builder;

@Builder
public record Location(
        Long locationId,
        String latitude,
        String longitude
) {
}
