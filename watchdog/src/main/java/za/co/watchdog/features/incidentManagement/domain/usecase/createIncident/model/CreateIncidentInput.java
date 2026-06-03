package za.co.watchdog.features.incidentManagement.domain.usecase.createIncident.model;

import lombok.Builder;
import lombok.Data;
import za.co.watchdog.common.domain.model.TriggerSource;

@Data
@Builder
public class CreateIncidentInput {
    private TriggerSource triggerSource;
    private Long addressId;
}
