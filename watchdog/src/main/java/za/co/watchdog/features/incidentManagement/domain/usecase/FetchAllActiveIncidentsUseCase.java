package za.co.watchdog.features.incidentManagement.domain.usecase;

import za.co.watchdog.common.domain.usecase.UseCase;
import za.co.watchdog.features.incidentManagement.domain.model.Incident;
import za.co.watchdog.features.incidentManagement.domain.repository.IncidentManagementRepository;

import java.util.List;
import java.util.Optional;

public class FetchAllActiveIncidentsUseCase implements UseCase<Long, List<Incident>> {
    private final IncidentManagementRepository incidentManagementRepository;

    FetchAllActiveIncidentsUseCase(IncidentManagementRepository incidentManagementRepository) {
        this.incidentManagementRepository = incidentManagementRepository;
    }

    @Override
    public List<Incident> execute(Long securityCompanyId) {
        if (securityCompanyId != null) {
            Optional<List<Incident>> incidents = incidentManagementRepository.fetchActiveIncidentsBySecurityCompanyId(securityCompanyId);
            if (incidents.isPresent()) {
                //TODO: return list
            }
        } else {
            //TODO: No security Company linked
        }
    }
}
