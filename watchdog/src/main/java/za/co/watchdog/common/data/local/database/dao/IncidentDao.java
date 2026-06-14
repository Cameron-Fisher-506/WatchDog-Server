package za.co.watchdog.common.data.local.database.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import za.co.watchdog.common.data.local.database.model.IncidentEntity;

import java.util.List;
import java.util.Optional;

@Repository
public interface IncidentDao extends JpaRepository<IncidentEntity, Long> {
    @Query("SELECT incidentEntity " +
            " FROM IncidentEntity incidentEntity" +
            " WHERE incidentEntity.securityCompanyEntity.securityCompanyId = :securityCompanyId AND " +
            "(incidentEntity.incidentStatus != za.co.watchdog.common.domain.model.IncidentStatus.CANCELLED AND " +
            "incidentEntity.incidentStatus != za.co.watchdog.common.domain.model.IncidentStatus.RESOLVED)")
    Optional<List<IncidentEntity>> findAllBySecurityCompanyId(Long securityCompanyId);
}
