package za.co.watchdog.features.incidentManagement.domain.usecase;

import org.springframework.stereotype.Service;
import za.co.watchdog.common.domain.exception.ResourceNotFoundException;
import za.co.watchdog.common.domain.manager.SecurityManager;
import za.co.watchdog.common.domain.model.User;
import za.co.watchdog.common.domain.repository.UserManagementRepository;
import za.co.watchdog.common.domain.usecase.UseCase;
import za.co.watchdog.features.incidentManagement.domain.model.Admin;
import za.co.watchdog.features.incidentManagement.domain.model.Incident;
import za.co.watchdog.features.incidentManagement.domain.repository.IncidentManagementRepository;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

@Service
public class FetchActiveIncidentsUseCase implements UseCase<Void, List<Incident>> {
    private final UserManagementRepository userManagementRepository;
    private final IncidentManagementRepository incidentManagementRepository;
    private final SecurityManager securityManager;

    FetchActiveIncidentsUseCase(UserManagementRepository userManagementRepository, IncidentManagementRepository incidentManagementRepository, SecurityManager securityManager) {
        this.userManagementRepository = userManagementRepository;
        this.incidentManagementRepository = incidentManagementRepository;
        this.securityManager = securityManager;
    }

    @Override
    public List<Incident> execute(Void input) {
        User user = userManagementRepository.fetchUserByUsername(securityManager.getCurrentUsername()).orElseThrow(() -> new ResourceNotFoundException("User", "username", securityManager.getCurrentUsername()));
        Admin admin = incidentManagementRepository.fetchAdminByUserId(user.getUserId()).orElseThrow(() -> new ResourceNotFoundException("Admin", "userId", user.getUserId()));
        if (admin.getSecurityCompanyId() != null) {
            Optional<List<Incident>> incidents = incidentManagementRepository.fetchActiveIncidentsBySecurityCompanyId(admin.getSecurityCompanyId());
            return incidents.orElse(Collections.emptyList());
        } else {
            return Collections.emptyList();
        }
    }
}
