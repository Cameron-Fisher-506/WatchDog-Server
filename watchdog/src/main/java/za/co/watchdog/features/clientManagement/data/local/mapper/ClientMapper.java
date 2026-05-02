package za.co.watchdog.features.clientManagement.data.local.mapper;

import jakarta.persistence.EntityManager;
import org.springframework.stereotype.Component;
import za.co.watchdog.common.data.local.database.model.*;
import za.co.watchdog.common.domain.model.*;
import za.co.watchdog.features.clientManagement.domain.model.Location;
import za.co.watchdog.features.clientManagement.domain.model.Client;

@Component
public class ClientMapper {
    private final EntityManager entityManager;

    ClientMapper(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    public Client mapToClient(ClientEntity clientEntity) {
        return Client.builder()
                .clientId(clientEntity.getClientId())
                .name(clientEntity.getName())
                .surname(clientEntity.getSurname())
                .contactNumber(clientEntity.getContactNumber())
                .userId(clientEntity.getUserEntity().getUserId())
                .build();
    }

    private Hub mapToHub(HubEntity hubEntity) {
        return new Hub(
                hubEntity.getHubId(),
                hubEntity.getMacAddress(),
                hubEntity.getStatus(),
                hubEntity.getLastHeartbeat(),
                hubEntity.getClientEntity().getClientId(),
                hubEntity.getLocationEntity().getLocationId()
        );
    }

    private Location mapToLocation(LocationEntity locationEntity) {
        return Location.builder()
                .locationId(locationEntity.getLocationId())
                .longitude(locationEntity.getLongitude())
                .latitude(locationEntity.getLatitude())
                .vehicleId(locationEntity.getVehicleEntity().getVehicleId())
                .clientId(locationEntity.getClientEntity().getClientId())
                .build();
    }

    private Sensor mapToSenor(SensorEntity sensorEntity) {
        return new Sensor(
                sensorEntity.getSensorId(),
                sensorEntity.getType(),
                sensorEntity.getZoneName(),
                sensorEntity.getHubEntity().getHubId()
        );
    }

    private User mapToUser(UserEntity userEntity) {
        return User.builder()
                .userId(userEntity.getUserId())
                .emailAddress(userEntity.getEmailAddress())
                .createdAt(userEntity.getCreatedAt())
                .userRole(userEntity.getUserRole())
                .build();
    }

    public ClientEntity mapToClientEntity(Client client) {
        UserEntity userEntity = entityManager.getReference(UserEntity.class, client.getUserId());
        return ClientEntity.builder()
                .name(client.getName())
                .surname(client.getSurname())
                .contactNumber(client.getContactNumber())
                .userEntity(userEntity)
                .build();
    }

    private HubEntity mapToHubEntity(Hub hub) {
        ClientEntity clientEntity = entityManager.getReference(ClientEntity.class, hub.clientId());
        LocationEntity locationEntity = entityManager.getReference(LocationEntity.class, hub.locationId());
        return new HubEntity(
                hub.hubId(),
                hub.macAddress(),
                hub.status(),
                hub.lastHeartbeat(),
                clientEntity,
                locationEntity
        );
    }

    private LocationEntity mapToLocationEntity(Location location) {
        ClientEntity clientEntity = entityManager.getReference(ClientEntity.class, location.getClientId());
        VehicleEntity vehicleEntity = entityManager.getReference(VehicleEntity.class, location.getVehicleId());
        return LocationEntity.builder()
                .locationId(location.getLocationId())
                .latitude(location.getLatitude())
                .longitude(location.getLongitude())
                .vehicleEntity(vehicleEntity)
                .clientEntity(clientEntity)
                .build();
    }

    private SensorEntity mapToSenorEntity(Sensor sensor, Hub hub) {
        return new SensorEntity(
                sensor.sensorId(),
                sensor.type(),
                sensor.zoneName(),
                mapToHubEntity(hub)
        );
    }

    private UserEntity mapToUserEntity(User user) {
        return UserEntity.builder()
                .userId(user.getUserId())
                .emailAddress(user.getEmailAddress())
                .createdAt(user.getCreatedAt())
                .userRole(user.getUserRole())
                .build();
    }
}
