package za.co.watchdog.common.presentation.model;

public record LocationDto(
        String latitude,
        String longitude,
        AddressDto addressDto
) {
}
