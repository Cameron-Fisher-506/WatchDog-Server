package za.co.watchdog.features.incidentManagement.domain.model;

import lombok.Builder;
import lombok.Data;
import za.co.watchdog.common.data.local.database.model.ZoneEntity;
import za.co.watchdog.common.domain.model.User;

@Builder
@Data
public class Patrol {
    private Long patrolId;
    private String officerCode;
    private String name;
    private String surname;
    private String contactNumber;
    private Long userId;
    private Long securityCompanyId;
    private Long zoneId;
}
