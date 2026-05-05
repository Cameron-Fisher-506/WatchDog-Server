package za.co.watchdog.common.data.local.database.model;

import jakarta.persistence.*;
import lombok.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
@Table(name = "address")
public class AddressEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long addressId;
    private String latitude;
    private String longitude;
    private String addressLineOne;
    private String addressLineTwo;
    private String suburb;
    private String postalCode;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "clientId")
    private ClientEntity clientEntity;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "zoneId")
    private ZoneEntity zoneEntity;
}
