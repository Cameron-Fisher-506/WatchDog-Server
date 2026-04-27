package za.co.watchdog.common.data.local.database.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import za.co.watchdog.common.data.local.database.model.PatrolVehicleEntity;
import za.co.watchdog.common.domain.model.VehicleStatus;

import java.util.Optional;

@Repository
public interface PatrolVehicleDao extends JpaRepository<PatrolVehicleEntity, Long> {
    @Query("SELECT patrol_vehicle" +
            " FROM PatrolVehicleEntity patrol_vehicle" +
            " WHERE patrol_vehicle.vehicleEntity.vehicleStatus = :vehicleStatus" +
            " AND patrol_vehicle.patrolEntity.zoneEntity.zoneId = :zoneId" +
            " AND patrol_vehicle.vehicleEntity.zoneEntity.zoneId = :zoneId")
    Optional<PatrolVehicleEntity> findPatrolVehicleByZoneIdAndVehicleStatus(Long zoneId, VehicleStatus vehicleStatus);
}
