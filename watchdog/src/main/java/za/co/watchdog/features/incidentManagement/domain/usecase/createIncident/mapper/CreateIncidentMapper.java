package za.co.watchdog.features.incidentManagement.domain.usecase.createIncident.mapper;

import za.co.watchdog.features.incidentManagement.domain.model.Client;
import za.co.watchdog.features.incidentManagement.domain.model.Incident;
import za.co.watchdog.features.incidentManagement.domain.model.PatrolVehicle;
import za.co.watchdog.features.incidentManagement.domain.usecase.createIncident.model.CreateIncidentOutput;

public interface CreateIncidentMapper {
    CreateIncidentOutput mapToCreateIncidentOutput(Incident incident, PatrolVehicle patrolVehicle);
    Incident mapToIncident(Client client, Long addressId, Long patrolId);
}
