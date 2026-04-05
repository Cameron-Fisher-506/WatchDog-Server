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
    @OneToOne(cascade = CascadeType.PERSIST)
    @JoinColumn(name = "hubId")
    private HubEntity hubEntity;
    private String name;
    private String surname;
    private String contactNumber;

    @OneToOne(cascade = CascadeType.PERSIST)
    @JoinColumn(name = "addressId")
    private AddressEntity addressEntity;

    @ManyToOne(cascade = CascadeType.PERSIST)
    @JoinColumn(name = "locationId")
    private LocationEntity locationEntity;

    @OneToOne(fetch = FetchType.LAZY)
    @MapsId
    @JoinColumn(name = "userId")
    private UserEntity userEntity;
}
