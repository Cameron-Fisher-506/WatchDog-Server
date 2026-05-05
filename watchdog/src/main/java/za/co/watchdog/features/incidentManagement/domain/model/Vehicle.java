package za.co.watchdog.features.incidentManagement.domain.model;

import lombok.Builder;
import lombok.Data;
import za.co.watchdog.common.domain.model.VehicleStatus;

import java.time.Instant;

@Builder
@Data
public class Vehicle {
    private Long vehicleId;
    private String plateNumber;
    private String model;
    private String make;
    private String callSign;
    private VehicleStatus vehicleStatus;
    private Long lastServiceMileage;
    private Instant createdAt;
    private Long securityCompanyId;
    private Long zoneId;
}