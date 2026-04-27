package za.co.watchdog.features.incidentManagement.domain.model;

import jakarta.persistence.*;

public class Zone {
    private Long zoneId;
    private String name;
    private String code;
    private Boolean isActive;
}
