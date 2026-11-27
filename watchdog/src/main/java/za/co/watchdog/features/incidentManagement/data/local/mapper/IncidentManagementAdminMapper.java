package za.co.watchdog.features.incidentManagement.data.local.mapper;

import org.springframework.stereotype.Component;
import za.co.watchdog.common.data.local.database.model.AdminEntity;
import za.co.watchdog.features.incidentManagement.domain.model.Admin;

@Component
public class IncidentManagementAdminMapper {
    public Admin mapToAdmin(AdminEntity adminEntity) {
        return Admin.builder()
                .adminId(adminEntity.getAdminId())
                .employeeId(adminEntity.getEmployeeId())
                .department(adminEntity.getDepartment())
                .securityCompanyId(adminEntity.getSecurityCompanyEntity().getSecurityCompanyId())
                .userId(adminEntity.getUserEntity().getUserId())
                .build();
    }
}
