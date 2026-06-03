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
@Table(name = "guardian")
public class GuardianEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long guardianId;
    @OneToOne(fetch = FetchType.LAZY)
    @MapsId
    @JoinColumn(name = "clientId")
    private ClientEntity clientEntity;
    private String contactNumber;
    private Boolean isActive;
}
