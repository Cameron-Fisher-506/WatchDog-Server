package za.co.watchdog.common.data.local.database.model;

import jakarta.persistence.*;
import lombok.*;

@EqualsAndHashCode(callSuper = true)
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
@Table(name = "security_company")
public class SecurityCompanyEntity extends UserEntity {
    @ManyToOne(cascade = CascadeType.PERSIST)
    @JoinColumn(name = "userId")
    private ClientEntity clientEntity;

    @ManyToOne(cascade = CascadeType.PERSIST)
    @JoinColumn(name = "userId")
    private PatrolEntity patrolEntity;

    private String name;
    private String psiraLicense;
    private String contactNumber;

}
