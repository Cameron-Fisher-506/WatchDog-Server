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

    @OneToOne
    @JoinColumn(name = "clientId")
    private ClientEntity client;

    @OneToOne
    @JoinColumn(name = "securityCompanyId")
    private SecurityCompanyEntity securityCompany;

    @ManyToOne
    @JoinColumn(name = "patrolId")
    private PatrolEntity patrol;

    private String Status;
    private Instant createdAt;
}
