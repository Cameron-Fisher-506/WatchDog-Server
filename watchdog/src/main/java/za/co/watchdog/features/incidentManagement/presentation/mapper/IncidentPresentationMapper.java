package za.co.watchdog.features.incidentManagement.presentation.mapper;

import org.springframework.stereotype.Component;
import za.co.watchdog.features.incidentManagement.domain.model.*;
import za.co.watchdog.features.incidentManagement.domain.usecase.createIncident.model.CreateIncidentInput;
import za.co.watchdog.features.incidentManagement.domain.usecase.createIncident.model.CreateIncidentOutput;
import za.co.watchdog.features.incidentManagement.presentation.model.incident.IncidentRequestDto;
import za.co.watchdog.features.incidentManagement.presentation.model.incident.IncidentResponseDto;
import za.co.watchdog.features.incidentManagement.presentation.model.incident.dto.PatrolDto;
import za.co.watchdog.features.incidentManagement.presentation.model.incident.dto.SecurityCompanyDto;
import za.co.watchdog.features.incidentManagement.presentation.model.incident.dto.VehicleDto;

@Component
public class IncidentPresentationMapper {
    public CreateIncidentInput mapToCreateIncidentInput(IncidentRequestDto incidentRequestDto) {
        return CreateIncidentInput.builder()
                .triggerSource(incidentRequestDto.getTriggerSource())
                .addressId(incidentRequestDto.getAddressId())
                .build();
    }
    public IncidentResponseDto mapToIncidentResponseDto(CreateIncidentOutput createIncidentOutput) {
        return IncidentResponseDto.builder()
                .incidentId(createIncidentOutput.getIncidentId())
                .patrolDto(mapToPatrolDto(createIncidentOutput.getPatrolVehicle().getPatrol()))
                .incidentStatus(createIncidentOutput.getIncidentStatus())
                .vehicleDto(mapToVehicleDto(createIncidentOutput.getPatrolVehicle().getVehicle()))
                .createdAt(createIncidentOutput.getCreatedAt())
                .build();
    }

    private PatrolDto mapToPatrolDto(Patrol patrol) {
        return PatrolDto.builder()
                .name(patrol.getName())
                .surname(patrol.getSurname())
                .officerCode(patrol.getOfficerCode())
                .contactNumber(patrol.getContactNumber())
                .build();
    }

    private VehicleDto mapToVehicleDto(Vehicle vehicle) {
        return VehicleDto.builder()
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
