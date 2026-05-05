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

    @OneToOne(fetch = FetchType.LAZY)
    @MapsId
    @JoinColumn(name = "userId")
    private UserEntity userEntity;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "securityCompanyId")
    private SecurityCompanyEntity securityCompanyEntity;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "zoneId")
    private ZoneEntity zoneEntity;
}
