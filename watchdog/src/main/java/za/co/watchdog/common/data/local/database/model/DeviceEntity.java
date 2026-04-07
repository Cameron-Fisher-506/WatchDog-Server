package za.co.watchdog.common.data.local.database.model;

import jakarta.persistence.*;
import lombok.*;

import java.time.Instant;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Builder
@Entity
@Table(name = "device")
public class DeviceEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long deviceId;

    @ManyToOne
    @JoinColumn(name = "userId")
    private UserEntity userEntity;

    private String deviceFingerprint;
    private String deviceName;
    private Instant lastLoggedIn;
    private Boolean isTrusted;
}
