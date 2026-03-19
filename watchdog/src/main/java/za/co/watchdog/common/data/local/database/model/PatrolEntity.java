package za.co.watchdog.common.data.local.database.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity(name = "Patrol")
public class PatrolEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long patrolId;
    private String name;
    private String surname;
    private String contactNumber;
    private String emailAddress;
}
