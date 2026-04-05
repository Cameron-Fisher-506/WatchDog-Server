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
@Table(name = "security_company")
public class SecurityCompanyEntity {
    @Id
    private Long securityCompanyId;
    @ManyToOne(cascade = CascadeType.PERSIST)
    @JoinColumn(name = "clientUserId")
    private ClientEntity clientEntity;

    @ManyToOne(cascade = CascadeType.PERSIST)
    @JoinColumn(name = "patrolUserId")
    private PatrolEntity patrolEntity;

    private String name;
    private String psiraLicense;
    private String contactNumber;

    @OneToOne(fetch = FetchType.LAZY)
    @MapsId
    @JoinColumn(name = "userId")
    private UserEntity userEntity;

}
