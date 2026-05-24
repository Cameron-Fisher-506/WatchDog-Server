package za.co.watchdog.features.incidentManagement.domain.usecase.createIncident;

import org.springframework.stereotype.Service;
import za.co.watchdog.common.domain.exception.ResourceNotFoundException;
import za.co.watchdog.common.domain.manager.SecurityManager;
import za.co.watchdog.common.domain.model.TriggerSource;
import za.co.watchdog.common.domain.model.User;
import za.co.watchdog.common.domain.model.VehicleStatus;
import za.co.watchdog.common.domain.repository.UserManagementRepository;
import za.co.watchdog.common.domain.usecase.UseCase;
import za.co.watchdog.features.incidentManagement.domain.model.Address;
import za.co.watchdog.features.incidentManagement.domain.model.Client;
import za.co.watchdog.features.incidentManagement.domain.model.Incident;
import za.co.watchdog.features.incidentManagement.domain.model.PatrolVehicle;
import za.co.watchdog.features.incidentManagement.domain.repository.IncidentManagementRepository;
import za.co.watchdog.features.incidentManagement.domain.usecase.createIncident.mapper.CreateIncidentMapper;
import za.co.watchdog.features.incidentManagement.domain.usecase.createIncident.model.CreateIncidentInput;
import za.co.watchdog.features.incidentManagement.domain.usecase.createIncident.model.CreateIncidentOutput;

@Service
public class CreateIncidentUseCase implements UseCase<CreateIncidentInput, CreateIncidentOutput> {
    private final IncidentManagementRepository incidentManagementRepository;
    private final UserManagementRepository userManagementRepository;
    private final SecurityManager securityManager;
    private final CreateIncidentMapper createIncidentMapper;

    CreateIncidentUseCase(
            IncidentManagementRepository incidentManagementRepository,
            UserManagementRepository userManagementRepository,
            CreateIncidentMapper createIncidentMapper,
            SecurityManager securityManager
    ) {
        this.incidentManagementRepository = incidentManagementRepository;
        this.userManagementRepository = userManagementRepository;
        this.createIncidentMapper = createIncidentMapper;
        this.securityManager = securityManager;

    }

    @Override
    public CreateIncidentOutput execute(CreateIncidentInput input) {
        User user = userManagementRepository.fetchUserByUsername(securityManager.getCurrentUsername()).orElseThrow(() -> new ResourceNotFoundException("User", "username", securityManager.getCurrentUsername()));
        Client client = incidentManagementRepository.fetchClientByUserId(user.getUserId()).orElseThrow(() -> new ResourceNotFoundException("Client", "userId", user.getUserId()));
        if (input.getTriggerSource() != TriggerSource.SMARTPHONE_GPS) {
            Address address = incidentManagementRepository.fetchAddressById(input.getAddressId()).orElseThrow(() -> new ResourceNotFoundException("Address", "addressId", input.getAddressId()));
            PatrolVehicle patrolVehicle = incidentManagementRepository.fetchPatrolVehicleByZoneIdAndVehicleStatus(address.zoneId(), VehicleStatus.AVAILABLE).orElseThrow(() -> new ResourceNotFoundException("PatrolVehicle", "zoneId", address.zoneId()));
            Incident incident = incidentManagementRepository.saveIncident(createIncidentMapper.mapToIncident(client, input.getAddressId(), patrolVehicle.getPatrol().getPatrolId())).orElseThrow(() -> new ResourceNotFoundException("Incident", "clientId", client.getClientId()));
            return createIncidentMapper.mapToCreateIncidentOutput(incident, patrolVehicle);
        } else {
            //Find patrol vehicle closet to client
            Incident incident = incidentManagementRepository.saveIncident(createIncidentMapper.mapToIncident(client, null, null)).orElseThrow(() -> new ResourceNotFoundException("Incident", "clientId", client.getClientId()));
            return createIncidentMapper.mapToCreateIncidentOutput(incident, null);
        }
    }
}
