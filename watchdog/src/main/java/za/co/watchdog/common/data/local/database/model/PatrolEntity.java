package za.co.watchdog.common.data.local.database.model;

import jakarta.persistence.*;
import lombok.*;


@EqualsAndHashCode(callSuper = true)
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
@Table(name = "Patrol")
public class PatrolEntity extends UserEntity {
    private String officerCode;
    private String name;
    private String surname;
    private String contactNumber;

    @ManyToOne(cascade = CascadeType.PERSIST)
    @JoinColumn(name = "locationId")
    private LocationEntity locationEntity;
}
