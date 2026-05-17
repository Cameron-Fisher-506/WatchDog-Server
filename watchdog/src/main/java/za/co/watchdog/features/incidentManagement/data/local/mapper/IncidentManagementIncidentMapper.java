package za.co.watchdog.features.incidentManagement.data.local.mapper;

import jakarta.persistence.EntityManager;
import org.springframework.stereotype.Component;
import za.co.watchdog.common.data.local.database.model.*;
import za.co.watchdog.features.incidentManagement.domain.model.Address;
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
                .addressId(incidentEntity.getAddressEntity().getAddressId())
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
                .addressEntity(entityManager.getReference(AddressEntity.class, incident.getAddressId()))
                .securityCompanyEntity(entityManager.getReference(SecurityCompanyEntity.class, incident.getSecurityCompanyId()))
                .build();
    }

    public AddressEntity mapToAddressEntity(Address address, Long clientId, Long zoneId) {
        return AddressEntity.builder()
                .latitude(address.latitude())
                .longitude(address.longitude())
                .addressLineOne(address.addressLineOne())
                .addressLineTwo(address.addressLineTwo())
                .suburb(address.suburb())
                .postalCode(address.postalCode())
                .clientEntity(entityManager.getReference(ClientEntity.class, clientId))
                .zoneEntity(entityManager.getReference(ZoneEntity.class, zoneId))
                .build();
    }

    public Address mapToAddress(AddressEntity addressEntity) {
        return Address.builder()
                .latitude(addressEntity.getLatitude())
                .longitude(addressEntity.getLongitude())
                .addressLineOne(addressEntity.getAddressLineOne())
                .addressLineTwo(addressEntity.getAddressLineTwo())
                .suburb(addressEntity.getSuburb())
                .postalCode(addressEntity.getPostalCode())
                .clientId(addressEntity.getClientEntity().getClientId())
                .zoneId(addressEntity.getZoneEntity().getZoneId())
                .build();
    }
}
