package za.co.watchdog.features.incidentManagement.domain.model;

import lombok.Builder;
import lombok.Data;

import java.time.Instant;

@Data
@Builder
public class PatrolVehicle {
    private Long patrolVehicleId;
    private Patrol patrol;
    private Vehicle vehicle;
    private Instant assignedAt;
    private Boolean isActive;
}
