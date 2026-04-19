package za.co.watchdog.features.incidentManagement.data.local.dataSource;

import org.springframework.stereotype.Component;
import za.co.watchdog.common.data.local.common.DatabaseResponse;
import za.co.watchdog.common.data.local.database.dao.IncidentDao;
import za.co.watchdog.common.data.local.database.dao.LocationDao;
import za.co.watchdog.common.data.local.database.model.IncidentEntity;
import za.co.watchdog.common.data.local.database.model.LocationEntity;

@Component
public class IncidentManagementLocalDataSourceImpl implements IncidentManagementLocalDataSource {
    private final IncidentDao incidentDao;
    private final LocationDao locationDao;

    IncidentManagementLocalDataSourceImpl(IncidentDao incidentDao, LocationDao locationDao) {
        this.incidentDao = incidentDao;
        this.locationDao = locationDao;
    }

    @Override
    public DatabaseResponse<IncidentEntity> saveIncidentEntity(IncidentEntity incidentEntity) {
        try {
            return DatabaseResponse.success(incidentDao.save(incidentEntity));
        } catch(Exception e) {
            return DatabaseResponse.error(e.getMessage());
        }
    }

    @Override
    public DatabaseResponse<LocationEntity> saveLocationEntity(LocationEntity locationEntity) {
        try {
            return DatabaseResponse.success(locationDao.save(locationEntity));
        } catch(Exception e) {
            return DatabaseResponse.error(e.getMessage());
        }
    }
}
