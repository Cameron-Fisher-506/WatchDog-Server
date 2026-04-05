package za.co.watchdog.common.domain.model;

import lombok.Builder;

@Builder
public record Address(
        Long addressId,
        String latitude,
        String longitude,
        String addressLineOne,
        String addressLineTwo,
        String suburb,
        String postalCode
) {
}
