package za.co.watchdog.common.data.local.database.model;

import jakarta.persistence.*;
import lombok.*;

import java.time.Instant;
import java.util.UUID;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
@Table(name = "vehicle")
public class VehicleEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long vehicleId;

    private String plateNumber;
    private String model;
    private String callSign;
    @Enumerated(EnumType.STRING)
    private VehicleStatus vehicleStatus;
    private Long lastServiceMileage;
    private Instant createdAt;
}