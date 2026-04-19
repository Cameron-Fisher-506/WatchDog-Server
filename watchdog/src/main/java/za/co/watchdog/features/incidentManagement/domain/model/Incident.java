package za.co.watchdog.features.incidentManagement.domain.model;

import lombok.Builder;
import lombok.Data;

import java.time.Instant;

@Data
@Builder
public class Incident {
    private Long incidentId;
    private Long clientId;
    private Long securityCompanyId;
    private Long patrolId;
    private Long locationId;
    private String Status;
    private Instant createdAt;
}
