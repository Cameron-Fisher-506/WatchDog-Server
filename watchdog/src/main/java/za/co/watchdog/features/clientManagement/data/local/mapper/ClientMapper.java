package za.co.watchdog.features.clientManagement.data.local.mapper;

import jakarta.persistence.EntityManager;
import org.springframework.stereotype.Component;
import za.co.watchdog.common.data.local.database.model.*;
import za.co.watchdog.common.data.local.database.model.UserRole;
import za.co.watchdog.common.domain.model.*;

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

    private Address mapToAddress(AddressEntity addressEntity) {
        return new Address(
                addressEntity.getAddressLineOne(),
                addressEntity.getAddressLineTwo(),
                addressEntity.getSuburb(),
                addressEntity.getPostalCode()
        );
    }

    private Location mapToLocation(LocationEntity locationEntity) {
        return new Location(
                locationEntity.getLocationId(),
                locationEntity.getLatitude(),
                locationEntity.getLongitude(),
                mapToAddress(locationEntity.getAddressEntity()),
                locationEntity.getClientEntity().getClientId(),
                locationEntity.getVehicleEntity().getVehicleId(),
                locationEntity.getZoneEntity().getZoneId()
        );
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
                .userRole(mapToUserRole(userEntity.getUserRole()))
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

    private AddressEntity mapToAddressEntity(Address address) {
        return new AddressEntity(
                address.addressLineOne(),
                address.addressLineTwo(),
                address.suburb(),
                address.postalCode()
        );
    }

    private LocationEntity mapToLocationEntity(Location location) {
        ClientEntity clientEntity = entityManager.getReference(ClientEntity.class, location.clientId());
        VehicleEntity vehicleEntity = entityManager.getReference(VehicleEntity.class, location.vehicleId());
        ZoneEntity zoneEntity = entityManager.getReference(ZoneEntity.class, location.zoneId());
        return new LocationEntity(
                location.locationId(),
                location.latitude(),
                location.longitude(),
                mapToAddressEntity(location.address()),
                clientEntity,
                vehicleEntity,
                zoneEntity
        );
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
                .userRole(mapToUserRole(user.getUserRole()))
                .build();
    }

    private UserRole mapToUserRole(za.co.watchdog.common.domain.model.UserRole userRole) {
        return UserRole.valueOf(userRole.name());
    }

    private za.co.watchdog.common.domain.model.UserRole mapToUserRole(UserRole userRole) {
        return za.co.watchdog.common.domain.model.UserRole.valueOf(userRole.name());
    }
}
