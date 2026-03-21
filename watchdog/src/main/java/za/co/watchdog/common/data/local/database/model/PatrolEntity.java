package za.co.watchdog.common.data.local.database.model;

import jakarta.persistence.*;
import lombok.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
@Table(name = "Patrol")
public class PatrolEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long patrolId;
    private String name;
    private String surname;
    private String contactNumber;
    private String emailAddress;
}
