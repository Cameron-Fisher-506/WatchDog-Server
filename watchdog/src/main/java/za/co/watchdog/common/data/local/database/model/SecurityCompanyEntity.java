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
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long securityCompanyId;
    private String name;
    private String psiraLicense;
    private String contactNumber;

    @OneToOne(fetch = FetchType.LAZY)
    @MapsId
    @JoinColumn(name = "userId")
    private UserEntity userEntity;

}
