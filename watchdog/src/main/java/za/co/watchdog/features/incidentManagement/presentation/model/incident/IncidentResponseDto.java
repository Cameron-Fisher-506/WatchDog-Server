package za.co.watchdog.features.incidentManagement.presentation.model.incident;

import lombok.Builder;
import za.co.watchdog.common.domain.model.IncidentStatus;
import za.co.watchdog.common.domain.model.TriggerSource;
import za.co.watchdog.features.incidentManagement.presentation.model.incident.dto.*;

import java.time.Instant;

@Builder
public record IncidentResponseDto(
        Long incidentId,
        PatrolDto patrolDto,
        VehicleDto vehicleDto,
        IncidentStatus incidentStatus,
        Instant createdAt
) {

}
