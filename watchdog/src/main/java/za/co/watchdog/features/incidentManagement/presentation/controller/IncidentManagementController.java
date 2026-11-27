package za.co.watchdog.features.incidentManagement.presentation.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.method.annotation.SseEmitter;
import za.co.watchdog.common.presentation.common.ApiSuccessResponse;
import za.co.watchdog.features.incidentManagement.domain.usecase.*;
import za.co.watchdog.features.incidentManagement.domain.usecase.createIncident.CreateIncidentUseCase;
import za.co.watchdog.features.incidentManagement.domain.usecase.createIncident.model.CreateIncidentInput;
import za.co.watchdog.features.incidentManagement.domain.usecase.createIncident.model.CreateIncidentOutput;
import za.co.watchdog.features.incidentManagement.presentation.mapper.IncidentPresentationMapper;
import za.co.watchdog.features.incidentManagement.presentation.model.incident.IncidentRequestDto;
import za.co.watchdog.features.incidentManagement.presentation.model.incident.IncidentResponseDto;
import za.co.watchdog.features.incidentManagement.presentation.model.incidentUpdateLocation.IncidentUpdateLocationRequestDto;

import java.io.IOException;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

@RestController
@RequestMapping("api/v1/incident-management")
public class IncidentManagementController {
    private final CreateIncidentUseCase createIncidentUseCase;
    private final IncidentPresentationMapper incidentPresentationMapper;
    private final List<SseEmitter> sseEmitters = new CopyOnWriteArrayList<>();

    IncidentManagementController(
            CreateIncidentUseCase createIncidentUseCase,
            IncidentPresentationMapper incidentPresentationMapper
    ) {
        this.createIncidentUseCase = createIncidentUseCase;
        this.incidentPresentationMapper = incidentPresentationMapper;
    }

    @GetMapping(value = "/control-room/stream", produces = MediaType.TEXT_EVENT_STREAM_VALUE)
    public SseEmitter streamIncidents() {
        SseEmitter sseEmitter = new SseEmitter(0L);

        sseEmitters.add(sseEmitter);

        sseEmitter.onCompletion(() -> this.sseEmitters.remove(sseEmitter));
        sseEmitter.onTimeout(() -> this.sseEmitters.remove(sseEmitter));
        sseEmitter.onError((ex) -> this.sseEmitters.remove(sseEmitter));

        try {
            sseEmitter.send(SseEmitter.event().name("INIT").data("Connected to WatchDog Live Stream"));
        } catch(IOException e) {
            sseEmitters.remove(sseEmitter);
        }

        return sseEmitter;
    }

    public void broadcastIncidentsToControlRoom(Object data) {
        List<SseEmitter> deadEmitters = new CopyOnWriteArrayList<>();

        for (SseEmitter sseEmitter: this.sseEmitters) {
            try {
                sseEmitter.send(SseEmitter.event()
                        .name("INCIDENT_ALERT")
                        .id(String.valueOf(System.currentTimeMillis()))
                        .data(data, MediaType.APPLICATION_JSON)
                );
            } catch(IOException e) {
                deadEmitters.add(sseEmitter);
            }
        }

        this.sseEmitters.removeAll(deadEmitters);
    }

    @PostMapping("/incident")
    ResponseEntity<ApiSuccessResponse<IncidentResponseDto>> sendIncident(@RequestBody IncidentRequestDto incidentRequestDto) {
        CreateIncidentInput createIncidentInput = incidentPresentationMapper.mapToCreateIncidentInput(incidentRequestDto);
        CreateIncidentOutput CreateIncidentOutput = createIncidentUseCase.execute(createIncidentInput);
        return new ResponseEntity<>(ApiSuccessResponse.ok(incidentPresentationMapper.mapToIncidentResponseDto(CreateIncidentOutput)), HttpStatus.OK);
    }

    @PostMapping("/update-location")
    ResponseEntity<HttpStatus> updateLocation(@RequestBody IncidentUpdateLocationRequestDto incidentUpdateLocationRequestDto) {

        return new ResponseEntity<>(HttpStatus.OK);
    }
}
