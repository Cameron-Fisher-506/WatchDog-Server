package za.co.watchdog.features.incidentManagement.domain.usecase;

import org.springframework.stereotype.Service;
import za.co.watchdog.common.domain.exception.ResourceNotFoundException;
import za.co.watchdog.common.domain.manager.SecurityManager;
import za.co.watchdog.common.domain.model.User;
import za.co.watchdog.common.domain.repository.UserManagementRepository;
import za.co.watchdog.common.domain.usecase.UseCase;
import za.co.watchdog.features.incidentManagement.domain.model.Incident;
import za.co.watchdog.features.incidentManagement.domain.model.SecurityCompany;
import za.co.watchdog.features.incidentManagement.domain.repository.IncidentManagementRepository;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

@Service
public class FetchAllActiveIncidentsUseCase implements UseCase<Long, List<Incident>> {
    private final UserManagementRepository userManagementRepository;
    private final IncidentManagementRepository incidentManagementRepository;
    private final SecurityManager securityManager;

    FetchAllActiveIncidentsUseCase(UserManagementRepository userManagementRepository, IncidentManagementRepository incidentManagementRepository, SecurityManager securityManager) {
        this.userManagementRepository = userManagementRepository;
        this.incidentManagementRepository = incidentManagementRepository;
        this.securityManager = securityManager;
    }

    @Override
    public List<Incident> execute(Long securityCompanyId) {
        User user = userManagementRepository.fetchUserByUsername(securityManager.getCurrentUsername()).orElseThrow(() -> new ResourceNotFoundException("User", "username", securityManager.getCurrentUsername()));

        if (securityCompanyId != null) {
            Optional<List<Incident>> incidents = incidentManagementRepository.fetchActiveIncidentsBySecurityCompanyId(securityCompanyId);
            return incidents.orElse(Collections.emptyList());
        } else {
            return Collections.emptyList();
        }
    }
}
