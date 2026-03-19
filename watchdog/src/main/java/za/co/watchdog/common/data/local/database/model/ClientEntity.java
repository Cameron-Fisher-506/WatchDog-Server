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
@Entity(name = "Client")
public class ClientEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long clientId;

    @OneToOne
    @JoinColumn(name = "hubId")
    private HubEntity hub;
    private String name;
    private String surname;
    private String contactNumber;
    private String emailAddress;

    @OneToOne
    @JoinColumn(name = "addressId")
    private AddressEntity address;

    @ManyToOne
    @JoinColumn(name = "locationId")
    private LocationEntity location;

}
