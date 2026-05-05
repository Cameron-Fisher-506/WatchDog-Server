package za.co.watchdog.common.data.local.database.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.Instant;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
@Table(name = "incident_tracking")
public class IncidentTrackingEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long incidentTrackingId;

    @ManyToOne
    @JoinColumn(name = "incidentId")
    private IncidentEntity incidentEntity;

    private String latitude;
    private String longitude;
    private Instant capturedAt;
}
