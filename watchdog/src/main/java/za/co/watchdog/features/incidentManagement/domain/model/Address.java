package za.co.watchdog.features.incidentManagement.domain.model;

import lombok.Builder;

@Builder
public record Address(
        String addressLineOne,
        String addressLineTwo,
        String suburb,
        String postalCode
) {
}
