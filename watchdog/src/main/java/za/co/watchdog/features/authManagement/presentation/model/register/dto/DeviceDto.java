package za.co.watchdog.features.authManagement.presentation.model.register.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.Instant;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Builder
public class DeviceDto {
    private String deviceName;
    private Instant lastLoggedIn;
}
