package za.co.watchdog.features.incidentManagement.data.local.dataSource;

import za.co.watchdog.common.data.local.common.DatabaseResponse;
import za.co.watchdog.common.data.local.database.model.IncidentEntity;
import za.co.watchdog.common.data.local.database.model.LocationEntity;

public interface IncidentManagementLocalDataSource {
    public DatabaseResponse<IncidentEntity> saveIncidentEntity(IncidentEntity incidentEntity);
    public DatabaseResponse<LocationEntity> saveLocationEntity(LocationEntity locationEntity);
}
