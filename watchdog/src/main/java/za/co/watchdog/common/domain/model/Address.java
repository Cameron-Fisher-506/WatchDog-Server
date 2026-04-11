package za.co.watchdog.common.domain.model;

import lombok.Builder;

@Builder
public record Address(
        String addressLineOne,
        String addressLineTwo,
        String suburb,
        String postalCode
) {
}
