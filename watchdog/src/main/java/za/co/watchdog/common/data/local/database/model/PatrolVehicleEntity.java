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
@Table(name = "patrol_vehicle")
public class PatrolVehicleEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long patrolVehicleId;
    @ManyToOne
    @JoinColumn(name = "patrolId")
    private PatrolEntity patrolEntity;
    @ManyToOne
    @JoinColumn(name = "vehicleId")
    private VehicleEntity vehicleEntity;
    private Instant assignedAt;
    private Boolean isActive;
}
