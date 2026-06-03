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

import java.util.Optional;

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
        // Notify the control room

        if (input.getTriggerSource() != TriggerSource.SMARTPHONE_GPS) {
            Optional<Address> address = incidentManagementRepository.fetchAddressById(input.getAddressId());
            if (address.isPresent()) {
                Optional<PatrolVehicle> patrolVehicle = incidentManagementRepository.fetchPatrolVehicleByZoneIdAndVehicleStatus(address.get().zoneId(), VehicleStatus.AVAILABLE);
                if (patrolVehicle.isPresent()) {
                    Incident incident = incidentManagementRepository.saveIncident(createIncidentMapper.mapToIncident(client, input.getAddressId(), patrolVehicle.get().getPatrol().getPatrolId())).orElseThrow(() -> new ResourceNotFoundException("Incident", "clientId", client.getClientId()));
                    return createIncidentMapper.mapToCreateIncidentOutput(incident, patrolVehicle.get());
                } else {
                    Incident incident = incidentManagementRepository.saveIncident(createIncidentMapper.mapToIncident(client, input.getAddressId(), null)).orElseThrow(() -> new ResourceNotFoundException("Incident", "clientId", client.getClientId()));
                    return createIncidentMapper.mapToCreateIncidentOutput(incident, null);
                }
            } else {
                Incident incident = incidentManagementRepository.saveIncident(createIncidentMapper.mapToIncident(client, null, null)).orElseThrow(() -> new ResourceNotFoundException("Incident", "clientId", client.getClientId()));
                return createIncidentMapper.mapToCreateIncidentOutput(incident, null);
            }
        } else {
            //TODO: Find patrol vehicle closet to the user
            Incident incident = incidentManagementRepository.saveIncident(createIncidentMapper.mapToIncident(client, null, null)).orElseThrow(() -> new ResourceNotFoundException("Incident", "clientId", client.getClientId()));
            return createIncidentMapper.mapToCreateIncidentOutput(incident, null);
        }
    }
}