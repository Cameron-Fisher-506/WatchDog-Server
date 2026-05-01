package za.co.watchdog.common.data.local.database.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import za.co.watchdog.common.data.local.database.model.VehicleEntity;

import java.util.List;

@Repository
public interface VehicleDao extends JpaRepository<VehicleEntity, Long> {
    public VehicleEntity findByZoneId(Long zoneId);
}
