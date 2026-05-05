package za.co.watchdog.features.incidentManagement.presentation.model.incident;

import lombok.Builder;
import lombok.Data;

@Builder
@Data
public class IncidentRequestDto {
    private Long locationId;
}
