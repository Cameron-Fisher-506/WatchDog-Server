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
@Table(name = "incident")
public class IncidentEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long incidentId;

    @ManyToOne(cascade = CascadeType.PERSIST)
    @JoinColumn(name = "clientId")
    private ClientEntity clientEntity;

    @ManyToOne(cascade = CascadeType.PERSIST)
    @JoinColumn(name = "securityCompanyId")
    private SecurityCompanyEntity securityCompanyEntity;

    @ManyToOne(cascade = CascadeType.PERSIST)
    @JoinColumn(name = "patrolId")
    private PatrolEntity patrolEntity;

    @ManyToOne(cascade = CascadeType.PERSIST)
    @JoinColumn(name = "addressId")
    private AddressEntity addressEntity;

    private String Status;
    private Instant createdAt;
}
