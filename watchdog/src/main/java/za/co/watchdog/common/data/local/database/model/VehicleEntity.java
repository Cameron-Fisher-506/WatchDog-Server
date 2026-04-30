package za.co.watchdog.common.data.local.database.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import za.co.watchdog.common.domain.model.VehicleStatus;

import java.time.Instant;

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
    private String make;
    private String callSign;
    @Enumerated(EnumType.STRING)
    private VehicleStatus vehicleStatus;
    private Long lastServiceMileage;
    private Instant createdAt;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "securityCompanyId")
    private SecurityCompanyEntity securityCompanyEntity;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "zoneId")
    private ZoneEntity zoneEntity;
}