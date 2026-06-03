package za.co.watchdog.features.incidentManagement.domain.usecase.createIncident.mapper;

import org.springframework.stereotype.Component;
import za.co.watchdog.common.domain.model.IncidentStatus;
import za.co.watchdog.features.incidentManagement.domain.model.Client;
import za.co.watchdog.features.incidentManagement.domain.model.Incident;
import za.co.watchdog.features.incidentManagement.domain.model.PatrolVehicle;
import za.co.watchdog.features.incidentManagement.domain.usecase.createIncident.model.CreateIncidentOutput;

import java.time.Instant;

@Component
public class CreateIncidentMapperImpl implements CreateIncidentMapper {
    @Override
    public CreateIncidentOutput mapToCreateIncidentOutput(Incident incident, PatrolVehicle patrolVehicle) {
        return CreateIncidentOutput.builder()
                .incidentId(incident.getIncidentId())
                .incidentStatus(incident.getIncidentStatus())
                .createdAt(incident.getCreatedAt())
                .patrolVehicle(patrolVehicle)
                .build();
    }

    @Override
    public Incident mapToIncident(Client client, Long addressId, Long patrolId) {
        return Incident.builder()
                .incidentStatus(IncidentStatus.TRIGGERED)
                .clientId(client.getClientId())
                .addressId(addressId)
                .createdAt(Instant.now())
                .securityCompanyId(client.getSecurityCompanyId())
                .patrolId(patrolId)
                .build();
    }
}
