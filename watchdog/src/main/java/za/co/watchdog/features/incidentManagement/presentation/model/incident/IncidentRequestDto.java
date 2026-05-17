package za.co.watchdog.features.incidentManagement.presentation.model.incident;

import lombok.Builder;
import lombok.Data;
import za.co.watchdog.common.domain.model.TriggerSource;

@Builder
@Data
public class IncidentRequestDto {
    private TriggerSource triggerSource;
    private Long addressId;
}
