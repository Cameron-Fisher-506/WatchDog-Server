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
@Table(name = "Patrol")
public class PatrolEntity {
    @Id
    private Long patrolId;
    private String officerCode;
    private String name;
    private String surname;
    private String contactNumber;

    @ManyToOne(cascade = CascadeType.PERSIST)
    @JoinColumn(name = "locationId")
    private LocationEntity locationEntity;

    @OneToOne(fetch = FetchType.LAZY)
    @MapsId
    @JoinColumn(name = "userId")
    private UserEntity userEntity;
}
