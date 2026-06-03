package za.co.watchdog.features.incidentManagement.domain.usecase.createIncident.model;

import lombok.Builder;
import lombok.Data;
import za.co.watchdog.common.domain.model.IncidentStatus;
import za.co.watchdog.features.incidentManagement.domain.model.PatrolVehicle;

import java.time.Instant;

@Data
@Builder
public class CreateIncidentOutput {
    private Long incidentId;
    private IncidentStatus incidentStatus;
    private PatrolVehicle patrolVehicle;
    private Instant createdAt;
}
