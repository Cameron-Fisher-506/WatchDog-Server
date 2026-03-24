package za.co.watchdog.common.data.local.database.model;

import jakarta.persistence.*;
import lombok.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
@Table(name = "Patrol")
public class PatrolEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long patrolId;
    private String officerCode;
    private String name;
    private String surname;
    private String contactNumber;

    @ManyToOne(cascade = CascadeType.PERSIST)
    @JoinColumn(name = "locationId")
    private LocationEntity locationEntity;

    @OneToOne(cascade = CascadeType.PERSIST)
    @JoinColumn(name = "userId")
    private UserEntity userEntity;
}
