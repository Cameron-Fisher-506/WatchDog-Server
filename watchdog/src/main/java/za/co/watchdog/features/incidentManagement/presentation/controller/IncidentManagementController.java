package za.co.watchdog.features.incidentManagement.presentation.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import za.co.watchdog.common.domain.model.User;
import za.co.watchdog.common.domain.usecase.FetchCurrentUserByUsernameUseCase;
import za.co.watchdog.common.presentation.common.ApiSuccessResponse;
import za.co.watchdog.features.incidentManagement.domain.model.Client;
import za.co.watchdog.features.incidentManagement.domain.model.Incident;
import za.co.watchdog.features.incidentManagement.domain.model.Location;
import za.co.watchdog.features.incidentManagement.domain.model.PatrolVehicle;
import za.co.watchdog.features.incidentManagement.domain.usecase.*;
import za.co.watchdog.features.incidentManagement.presentation.mapper.IncidentPresentationMapper;
import za.co.watchdog.features.incidentManagement.presentation.model.incident.IncidentRequestDto;
import za.co.watchdog.features.incidentManagement.presentation.model.incident.IncidentResponseDto;

@RestController
@RequestMapping("api/v1/incident-management")
public class IncidentManagementController {
    private final CreateIncidentUseCase createIncidentUseCase;
    private final FetchCurrentUserByUsernameUseCase fetchCurrentUserByUsernameUseCase;
    private final FetchClientByUserIdUseCase fetchClientByUserIdUseCase;
    private final FetchAvailablePatrolVehicleByZoneIdUseCase fetchAvailablePatrolVehicleByZoneIdUseCase;
    private final FetchLocationByIdUseCase fetchLocationByIdUseCase;
    private final IncidentPresentationMapper incidentPresentationMapper;

    IncidentManagementController(
            CreateIncidentUseCase createIncidentUseCase,
            FetchCurrentUserByUsernameUseCase fetchCurrentUserByUsernameUseCase,
            FetchClientByUserIdUseCase fetchClientByUserIdUseCase,
            FetchAvailablePatrolVehicleByZoneIdUseCase fetchAvailablePatrolVehicleByZoneIdUseCase,
            FetchLocationByIdUseCase fetchLocationByIdUseCase,
            IncidentPresentationMapper incidentPresentationMapper
    ) {
        this.createIncidentUseCase = createIncidentUseCase;
        this.fetchCurrentUserByUsernameUseCase = fetchCurrentUserByUsernameUseCase;
        this.fetchClientByUserIdUseCase = fetchClientByUserIdUseCase;
        this.fetchAvailablePatrolVehicleByZoneIdUseCase = fetchAvailablePatrolVehicleByZoneIdUseCase;
        this.fetchLocationByIdUseCase = fetchLocationByIdUseCase;
        this.incidentPresentationMapper = incidentPresentationMapper;
    }

    @PostMapping("/incident")
    ResponseEntity<ApiSuccessResponse<IncidentResponseDto>> sendIncident(@RequestBody IncidentRequestDto incidentRequestDto) {
        User user = fetchCurrentUserByUsernameUseCase.execute(null);
        Client client = fetchClientByUserIdUseCase.execute(user.getUserId());
        Location location = fetchLocationByIdUseCase.execute(incidentRequestDto.getLocationId());
        PatrolVehicle patrolVehicle = fetchAvailablePatrolVehicleByZoneIdUseCase.execute(location.getZoneId());
        Incident incident = createIncidentUseCase.execute(incidentPresentationMapper.mapToIncident(client, incidentRequestDto.getLocationId(), patrolVehicle.getPatrol().getPatrolId()));

        return new ResponseEntity<>(ApiSuccessResponse.ok(incidentPresentationMapper.mapToIncidentResponseDto(incident, patrolVehicle)), HttpStatus.OK);
    }
}
