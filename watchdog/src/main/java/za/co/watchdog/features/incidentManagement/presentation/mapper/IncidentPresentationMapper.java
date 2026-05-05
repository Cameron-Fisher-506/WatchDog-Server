package za.co.watchdog.features.incidentManagement.presentation.mapper;

import org.springframework.stereotype.Component;
import za.co.watchdog.common.domain.model.IncidentStatus;
import za.co.watchdog.features.incidentManagement.domain.model.*;
import za.co.watchdog.features.incidentManagement.presentation.model.incident.IncidentResponseDto;
import za.co.watchdog.features.incidentManagement.presentation.model.incident.dto.PatrolDto;
import za.co.watchdog.features.incidentManagement.presentation.model.incident.dto.SecurityCompanyDto;
import za.co.watchdog.features.incidentManagement.presentation.model.incident.dto.VehicleDto;

import java.time.Instant;

@Component
public class IncidentPresentationMapper {
    public Incident mapToIncident(Client client, Long locationId, Long patrolId) {
        return Incident.builder()
                .incidentStatus(IncidentStatus.TRIGGERED)
                .clientId(client.getClientId())
                .locationId(locationId)
                .createdAt(Instant.now())
                .securityCompanyId(client.getSecurityCompanyId())
                .patrolId(patrolId)
                .build();
    }

    public IncidentResponseDto mapToIncidentResponseDto(Incident incident, PatrolVehicle patrolVehicle) {
        return IncidentResponseDto.builder()
                .incidentId(incident.getIncidentId())
                .patrolDto(mapToPatrolDto(patrolVehicle.getPatrol()))
                .incidentStatus(incident.getIncidentStatus())
                .vehicleDto(mapToVehicleDto(patrolVehicle.getVehicle()))
                .build();
    }

    private PatrolDto mapToPatrolDto(Patrol patrol) {
        return PatrolDto.builder()
                .patrolId(patrol.getPatrolId())
                .name(patrol.getName())
                .surname(patrol.getSurname())
                .officerCode(patrol.getOfficerCode())
                .contactNumber(patrol.getContactNumber())
                .build();
    }

    private VehicleDto mapToVehicleDto(Vehicle vehicle) {
        return VehicleDto.builder()
                .vehicleId(vehicle.getVehicleId())
                .plateNumber(vehicle.getPlateNumber())
                .model(vehicle.getModel())
                .build();
    }

    private SecurityCompanyDto mapToSecurityCompanyDto(SecurityCompany securityCompany) {
        return SecurityCompanyDto.builder()
                .securityCompanyId(securityCompany.securityCompanyId())
                .name(securityCompany.name())
                .contactNumber(securityCompany.contactNumber())
                .build();
    }
}
