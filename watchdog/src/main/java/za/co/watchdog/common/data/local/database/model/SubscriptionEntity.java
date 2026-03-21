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
@Table(name = "subscription")
public class SubscriptionEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long subscriptionId;

    @ManyToOne
    @JoinColumn(name = "clientId")
    private ClientEntity client;

    @ManyToOne
    @JoinColumn(name = "planId")
    private PlanEntity plan;

    private String status;
    private Instant startDate;
    private Instant endDate;
    private Instant canceledAt;
}
