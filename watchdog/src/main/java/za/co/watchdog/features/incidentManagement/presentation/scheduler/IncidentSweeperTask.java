package za.co.watchdog.features.incidentManagement.presentation.scheduler;

import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import za.co.watchdog.features.incidentManagement.domain.usecase.FetchAllActiveIncidentsUseCase;
import za.co.watchdog.features.incidentManagement.presentation.controller.IncidentManagementController;

@Component
public class IncidentSweeperTask {
    private final IncidentManagementController incidentManagementController;
    private final FetchAllActiveIncidentsUseCase fetchAllActiveIncidentsUseCase;

    IncidentSweeperTask(IncidentManagementController incidentManagementController, FetchAllActiveIncidentsUseCase fetchAllActiveIncidentsUseCase) {
        this.incidentManagementController = incidentManagementController;
        this.fetchAllActiveIncidentsUseCase = fetchAllActiveIncidentsUseCase;
    }

    @Scheduled(fixedDelay = 10000)
    public void executeSweeper() {
        incidentManagementController.broadcastIncidentsToControlRoom(fetchAllActiveIncidentsUseCase.execute());
    }
}
