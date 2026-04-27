package za.co.watchdog.features.incidentManagement.data.local.mapper;

import jakarta.persistence.EntityManager;
import org.springframework.stereotype.Component;
import za.co.watchdog.common.data.local.database.model.*;
import za.co.watchdog.features.incidentManagement.domain.model.Incident;

@Component
public class IncidentManagementIncidentMapper {
    private final EntityManager entityManager;

    IncidentManagementIncidentMapper(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    public Incident mapToIncident(IncidentEntity incidentEntity) {
        return Incident.builder()
                .incidentId(incidentEntity.getIncidentId())
                .clientId(incidentEntity.getClientEntity().getClientId())
                .incidentStatus(incidentEntity.getIncidentStatus())
                .createdAt(incidentEntity.getCreatedAt())
                .patrolId(incidentEntity.getPatrolEntity().getPatrolId())
                .locationId(incidentEntity.getLocationEntity().getLocationId())
                .securityCompanyId(incidentEntity.getSecurityCompanyEntity().getSecurityCompanyId())
                .build();
    }

    public IncidentEntity mapToIncidentEntity(Incident incident) {
        return IncidentEntity.builder()
                .incidentId(incident.getIncidentId())
                .clientEntity(entityManager.getReference(ClientEntity.class, incident.getClientId()))
                .incidentStatus(incident.getIncidentStatus())
                .createdAt(incident.getCreatedAt())
                .patrolEntity(entityManager.getReference(PatrolEntity.class, incident.getPatrolId()))
                .locationEntity(entityManager.getReference(LocationEntity.class, incident.getLocationId()))
                .securityCompanyEntity(entityManager.getReference(SecurityCompanyEntity.class, incident.getSecurityCompanyId()))
                .build();
    }
}
