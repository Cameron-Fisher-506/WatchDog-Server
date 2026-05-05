package za.co.watchdog.features.clientManagement.presentation.model.clientOnboarding.dto;

public record SensorDto(
        Long sensorId,
        String type,
        String zoneName
) {
}
