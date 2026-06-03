package za.co.watchdog.common.data.local.database.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Builder
@Entity
@Table(name = "address_guardian")
public class AddressGuardianEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long locationGuardianId;

    @ManyToOne
    @JoinColumn(name = "addressId")
    private AddressEntity addressEntity;

    @ManyToOne
    @JoinColumn(name = "guardianId")
    private GuardianEntity guardianEntity;
}
