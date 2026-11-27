package za.co.watchdog.features.incidentManagement.presentation.scheduler;

import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import za.co.watchdog.features.incidentManagement.domain.usecase.FetchActiveIncidentsUseCase;
import za.co.watchdog.features.incidentManagement.presentation.controller.IncidentManagementController;

@Component
public class IncidentSweeperTask {
    private final IncidentManagementController incidentManagementController;
    private final FetchActiveIncidentsUseCase fetchActiveIncidentsUseCase;

    IncidentSweeperTask(IncidentManagementController incidentManagementController, FetchActiveIncidentsUseCase fetchActiveIncidentsUseCase) {
        this.incidentManagementController = incidentManagementController;
        this.fetchActiveIncidentsUseCase = fetchActiveIncidentsUseCase;
    }

    @Scheduled(fixedDelay = 10000)
    public void executeSweeper() {
        incidentManagementController.broadcastIncidentsToControlRoom(fetchActiveIncidentsUseCase.execute(null));
    }
}
