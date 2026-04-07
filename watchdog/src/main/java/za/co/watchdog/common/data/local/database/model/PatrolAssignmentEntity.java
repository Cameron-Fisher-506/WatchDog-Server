package za.co.watchdog.common.data.local.database.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.Instant;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Builder
@Entity
@Table(name = "patrol_assignment")
public class PatrolAssignmentEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long patrolAssignmentId;
    @ManyToOne
    @JoinColumn(name = "patrolId")
    private PatrolEntity patrolEntity;
    @ManyToOne
    @JoinColumn(name = "locationId")
    private LocationEntity locationEntity;
    private Instant assignedAt;
    private Boolean isActive;
}
