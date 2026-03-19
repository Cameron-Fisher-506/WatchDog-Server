package za.co.watchdog.common.data.local.database.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity(name = "Sensor")
public class SensorEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long sensorId;
    private String type;
    private String zoneName;
}
