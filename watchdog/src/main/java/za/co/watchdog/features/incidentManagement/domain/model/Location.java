package za.co.watchdog.features.incidentManagement.domain.model;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class Location {
    private Long locationId;
    private String latitude;
    private String longitude;
    private Address address;
    private Long clientId;
    private Long vehicleId;
    private Long zoneId;
}
