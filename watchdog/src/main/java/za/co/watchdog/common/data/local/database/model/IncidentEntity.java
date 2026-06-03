package za.co.watchdog.common.data.local.database.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import za.co.watchdog.common.domain.model.IncidentStatus;
import za.co.watchdog.common.domain.model.TriggerSource;

import java.time.Instant;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
@Table(name = "incident")
public class IncidentEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long incidentId;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "clientId")
    private ClientEntity clientEntity;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "securityCompanyId")
    private SecurityCompanyEntity securityCompanyEntity;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "patrolId")
    private PatrolEntity patrolEntity;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "addressId")
    private AddressEntity addressEntity;

    private String latitude;
    private String longitude;

    @Enumerated(EnumType.STRING)
    private IncidentStatus incidentStatus;
    @Enumerated(EnumType.STRING)
    private TriggerSource triggerSource;
    private Instant createdAt;
}
