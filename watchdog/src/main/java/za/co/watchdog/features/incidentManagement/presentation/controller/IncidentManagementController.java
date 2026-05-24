package za.co.watchdog.features.incidentManagement.presentation.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import za.co.watchdog.common.domain.usecase.FetchCurrentUserByUsernameUseCase;
import za.co.watchdog.common.presentation.common.ApiSuccessResponse;
import za.co.watchdog.features.incidentManagement.domain.model.Incident;
import za.co.watchdog.features.incidentManagement.domain.usecase.*;
import za.co.watchdog.features.incidentManagement.domain.usecase.createIncident.CreateIncidentUseCase;
import za.co.watchdog.features.incidentManagement.domain.usecase.createIncident.model.CreateIncidentInput;
import za.co.watchdog.features.incidentManagement.domain.usecase.createIncident.model.CreateIncidentOutput;
import za.co.watchdog.features.incidentManagement.presentation.mapper.IncidentPresentationMapper;
import za.co.watchdog.features.incidentManagement.presentation.model.incident.IncidentRequestDto;
import za.co.watchdog.features.incidentManagement.presentation.model.incident.IncidentResponseDto;

@RestController
@RequestMapping("api/v1/incident-management")
public class IncidentManagementController {
    private final CreateIncidentUseCase createIncidentUseCase;
    private final IncidentPresentationMapper incidentPresentationMapper;

    IncidentManagementController(
            CreateIncidentUseCase createIncidentUseCase,
            IncidentPresentationMapper incidentPresentationMapper
    ) {
        this.createIncidentUseCase = createIncidentUseCase;
        this.incidentPresentationMapper = incidentPresentationMapper;
    }

    @PostMapping("/incident")
    ResponseEntity<ApiSuccessResponse<IncidentResponseDto>> sendIncident(@RequestBody IncidentRequestDto incidentRequestDto) {
        CreateIncidentInput createIncidentInput = incidentPresentationMapper.mapToCreateIncidentInput(incidentRequestDto);
        CreateIncidentOutput CreateIncidentOutput = createIncidentUseCase.execute(createIncidentInput);
        return new ResponseEntity<>(ApiSuccessResponse.ok(incidentPresentationMapper.mapToIncidentResponseDto(CreateIncidentOutput)), HttpStatus.OK);
    }

    /*@PostMapping("/update-location")
    ResponseEntity<ApiSuccessResponse<IncidentResponseDto>> updateLocation(@RequestBody )*/
}
