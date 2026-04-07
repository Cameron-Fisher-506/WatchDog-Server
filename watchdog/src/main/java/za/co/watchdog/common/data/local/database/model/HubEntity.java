package za.co.watchdog.common.data.local.database.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.Instant;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity(name = "hub")
public class HubEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long hubId;
    private String macAddress;
    private String status;
    private Instant lastHeartbeat;
}
