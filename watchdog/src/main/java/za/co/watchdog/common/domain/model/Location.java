package za.co.watchdog.common.domain.model;

import lombok.Builder;

@Builder
public record Location(
        Long locationId,
        String latitude,
        String longitude,
        Address address,
        Long clientId
) {
}
