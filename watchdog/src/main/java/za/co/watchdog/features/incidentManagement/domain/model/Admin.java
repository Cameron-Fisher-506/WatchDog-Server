package za.co.watchdog.features.incidentManagement.domain.model;

import lombok.Builder;
import lombok.Data;
import lombok.Getter;

@Data
@Getter
@Builder
public class Admin {
    private Long adminId;
    private String employeeId;
    private String department;
    private Long userId;
    private Long securityCompanyId;
}
