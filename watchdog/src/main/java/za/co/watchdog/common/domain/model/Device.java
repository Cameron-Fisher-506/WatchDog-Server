package za.co.watchdog.common.domain.model;

import lombok.Builder;
import lombok.Data;
import java.time.Instant;

@Data
@Builder
public class Device {
    private Long deviceId;
    private Long userId;
    private String deviceFingerprint;
    private String deviceName;
    private Instant lastLoggedIn;
    private Boolean isTrusted;
}
