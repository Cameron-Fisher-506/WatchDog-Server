package za.co.watchdog.features.incidentManagement.presentation.model.incident;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import za.co.watchdog.common.domain.model.TriggerSource;

@NoArgsConstructor
@AllArgsConstructor
@Builder
@Data
public class IncidentRequestDto {
    private TriggerSource triggerSource;
    private Long addressId;
}
