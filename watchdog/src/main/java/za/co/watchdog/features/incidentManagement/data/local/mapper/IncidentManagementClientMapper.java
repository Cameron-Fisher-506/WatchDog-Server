package za.co.watchdog.features.incidentManagement.data.local.mapper;

import org.springframework.stereotype.Component;
import za.co.watchdog.common.data.local.database.model.ClientEntity;
import za.co.watchdog.features.incidentManagement.domain.model.Client;

@Component
public class IncidentManagementClientMapper {
    public Client mapToClient(ClientEntity clientEntity) {
        return Client.builder()
                .clientId(clientEntity.getClientId())
                .name(clientEntity.getName())
                .contactNumber(clientEntity.getContactNumber())
                .userId(clientEntity.getUserEntity().getUserId())
                .build();
    }
}
