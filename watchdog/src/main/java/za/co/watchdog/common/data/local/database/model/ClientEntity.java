package za.co.watchdog.common.data.local.database.model;

import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.SuperBuilder;

@Data
@NoArgsConstructor
@AllArgsConstructor
@SuperBuilder
@Entity(name = "Client")
public class ClientEntity {
    @Id
    private Long clientId;
    private String name;
    private String surname;
    private String contactNumber;

    @OneToOne(fetch = FetchType.LAZY)
    @MapsId
    @JoinColumn(name = "userId")
    private UserEntity userEntity;

    @ManyToOne(cascade = CascadeType.PERSIST)
    @JoinColumn(name = "securityCompanyId")
    private SecurityCompanyEntity securityCompanyEntity;
}
