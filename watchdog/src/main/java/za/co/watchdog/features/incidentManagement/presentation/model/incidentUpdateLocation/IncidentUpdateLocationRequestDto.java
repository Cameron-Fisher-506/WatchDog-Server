package za.co.watchdog.features.incidentManagement.presentation.model.incidentUpdateLocation;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class IncidentUpdateLocationRequestDto {
    private Long incidentId;
    private String latitude;
    private String longitude;
}
