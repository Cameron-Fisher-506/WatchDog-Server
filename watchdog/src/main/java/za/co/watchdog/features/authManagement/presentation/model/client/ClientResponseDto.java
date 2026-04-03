package za.co.watchdog.features.authManagement.presentation.model.client;

public record ClientResponseDto(
        Long clientId,
        String token
) {
}
