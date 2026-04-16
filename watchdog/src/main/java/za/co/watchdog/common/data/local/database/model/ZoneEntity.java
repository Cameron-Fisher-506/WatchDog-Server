package za.co.watchdog.common.data.local.database.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
@Table(name = "zone")
public class ZoneEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long zoneId;
    private String name;
    private String code;
    private Boolean isActive;
    @ManyToOne(cascade = CascadeType.PERSIST)
    @JoinColumn(name = "vehicleId")
    private VehicleEntity vehicleEntity;
}
