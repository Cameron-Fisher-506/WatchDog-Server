package za.co.watchdog.features.incidentManagement.presentation.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import za.co.watchdog.common.domain.model.User;
import za.co.watchdog.common.domain.usecase.FetchCurrentUserByUsernameUseCase;
import za.co.watchdog.common.presentation.common.ApiSuccessResponse;
import za.co.watchdog.features.incidentManagement.domain.usecase.SaveIncidentUseCase;
import za.co.watchdog.features.incidentManagement.domain.usecase.SaveLocationUseCase;
import za.co.watchdog.features.incidentManagement.presentation.model.incident.IncidentRequestDto;
import za.co.watchdog.features.incidentManagement.presentation.model.incident.IncidentResponseDto;

@RestController
@RequestMapping("api/v1/incident-management")
public class IncidentManagementController {
    private final SaveIncidentUseCase saveIncidentUseCase;
    private final SaveLocationUseCase saveLocationUseCase;
    private final FetchCurrentUserByUsernameUseCase fetchCurrentUserByUsernameUseCase;

    IncidentManagementController(
            SaveIncidentUseCase saveIncidentUseCase,
            SaveLocationUseCase saveLocationUseCase,
            FetchCurrentUserByUsernameUseCase fetchCurrentUserByUsernameUseCase
    ) {
        this.saveIncidentUseCase = saveIncidentUseCase;
        this.saveLocationUseCase = saveLocationUseCase;
        this.fetchCurrentUserByUsernameUseCase = fetchCurrentUserByUsernameUseCase;
    }

    @PostMapping("/incident")
    ResponseEntity<ApiSuccessResponse<IncidentResponseDto>> sendIncident(@RequestBody IncidentRequestDto incidentRequestDto) {
        User user = fetchCurrentUserByUsernameUseCase.execute(null);

    }
}
