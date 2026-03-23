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

    @ManyToOne(cascade = CascadeType.PERSIST)
    @JoinColumn(name = "clientId")
    private ClientEntity clientEntity;

    @ManyToOne(cascade = CascadeType.PERSIST)
    @JoinColumn(name = "planId")
    private PlanEntity planEntity;

    private String status;
    private Instant startDate;
    private Instant endDate;
    private Instant canceledAt;
}
