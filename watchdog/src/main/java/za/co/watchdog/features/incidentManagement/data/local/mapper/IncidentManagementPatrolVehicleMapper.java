package za.co.watchdog.features.incidentManagement.data.local.mapper;

import org.springframework.stereotype.Component;
import za.co.watchdog.common.data.local.database.model.PatrolEntity;
import za.co.watchdog.common.data.local.database.model.PatrolVehicleEntity;
import za.co.watchdog.common.data.local.database.model.VehicleEntity;
import za.co.watchdog.features.incidentManagement.domain.model.Patrol;
import za.co.watchdog.features.incidentManagement.domain.model.PatrolVehicle;
import za.co.watchdog.features.incidentManagement.domain.model.Vehicle;

@Component
public class IncidentManagementPatrolVehicleMapper {
    public PatrolVehicle mapToPatrolVehicle(PatrolVehicleEntity patrolVehicleEntity) {
        return PatrolVehicle.builder()
                .patrolVehicleId(patrolVehicleEntity.getPatrolVehicleId())
                .vehicle(mapToVehicle(patrolVehicleEntity.getVehicleEntity()))
                .patrol(mapToPatrol(patrolVehicleEntity.getPatrolEntity()))
                .isActive(patrolVehicleEntity.getIsActive())
                .assignedAt(patrolVehicleEntity.getAssignedAt())
                .build();
    }

    private Vehicle mapToVehicle(VehicleEntity vehicleEntity) {
        return Vehicle.builder()
                .vehicleId(vehicleEntity.getVehicleId())
                .lastServiceMileage(vehicleEntity.getLastServiceMileage())
                .vehicleStatus(vehicleEntity.getVehicleStatus())
                .securityCompanyId(vehicleEntity.getSecurityCompanyEntity().getSecurityCompanyId())
                .zoneId(vehicleEntity.getZoneEntity().getZoneId())
                .createdAt(vehicleEntity.getCreatedAt())
                .callSign(vehicleEntity.getCallSign())
                .make(vehicleEntity.getMake())
                .plateNumber(vehicleEntity.getPlateNumber())
                .build();
    }

    private Patrol mapToPatrol(PatrolEntity patrolEntity) {
        return Patrol.builder()
                .name(patrolEntity.getName())
                .patrolId(patrolEntity.getPatrolId())
                .userId(patrolEntity.getUserEntity().getUserId())
                .contactNumber(patrolEntity.getContactNumber())
                .surname(patrolEntity.getSurname())
                .officerCode(patrolEntity.getOfficerCode())
                .securityCompanyId(patrolEntity.getSecurityCompanyEntity().getSecurityCompanyId())
                .zoneId(patrolEntity.getZoneEntity().getZoneId())
                .build();
    }
}
