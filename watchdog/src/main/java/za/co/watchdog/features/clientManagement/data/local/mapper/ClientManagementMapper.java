package za.co.watchdog.features.clientManagement.data.local.mapper;

import jakarta.persistence.EntityManager;
import org.springframework.stereotype.Component;
import za.co.watchdog.common.data.local.database.model.*;
import za.co.watchdog.common.domain.model.Hub;
import za.co.watchdog.common.domain.model.Sensor;
import za.co.watchdog.common.domain.model.User;
import za.co.watchdog.features.clientManagement.domain.model.Address;
import za.co.watchdog.features.clientManagement.domain.model.Client;
import za.co.watchdog.features.clientManagement.domain.model.Location;
import za.co.watchdog.features.clientManagement.domain.model.Vehicle;

@Component
public class ClientManagementMapper {
    private final EntityManager entityManager;

    ClientManagementMapper(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    public ClientEntity mapToClientEntity(Client client) {
        UserEntity userEntity = entityManager.getReference(UserEntity.class, client.getUserId());
        return ClientEntity.builder()
                .clientId(client.getUserId())
                .name(client.getName())
                .surname(client.getSurname())
                .contactNumber(client.getContactNumber())
                .userEntity(userEntity)
                .build();
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

    public Location mapToLocation(LocationEntity locationEntity) {
        return Location.builder()
                .locationId(locationEntity.getLocationId())
                .longitude(locationEntity.getLongitude())
                .latitude(locationEntity.getLatitude())
                .clientId(locationEntity.getClientEntity().getClientId())
                .vehicleId(locationEntity.getVehicleEntity().getVehicleId())
                .build();
    }

    public Address mapToAddress(AddressEntity addressEntity) {
        return Address.builder()
                .addressLineOne(addressEntity.getAddressLineOne())
                .addressLineTwo(addressEntity.getAddressLineTwo())
                .suburb(addressEntity.getSuburb())
                .postalCode(addressEntity.getPostalCode())
                .build();
    }

    public LocationEntity mapToLocationEntity(Location location) {
        return LocationEntity.builder()
                .locationId(location.getLocationId())
                .longitude(location.getLongitude())
                .latitude(location.getLatitude())
                .clientEntity(entityManager.getReference(ClientEntity.class, location.getClientId()))
                .vehicleEntity(entityManager.getReference(VehicleEntity.class, location.getVehicleId()))
                .build();
    }

    public AddressEntity mapToAddressEntity(Address address) {
        return AddressEntity.builder()
                .latitude(address.latitude())
                .longitude(address.longitude())
                .addressLineOne(address.addressLineOne())
                .addressLineTwo(address.addressLineTwo())
                .suburb(address.suburb())
                .postalCode(address.postalCode())
                .clientEntity(entityManager.getReference(ClientEntity.class, address.clientId()))
                .zoneEntity(entityManager.getReference(ZoneEntity.class, address.zoneId()))
                .build();
    }

    public Vehicle mapToVehicle(VehicleEntity vehicleEntity) {
        return Vehicle.builder()
                .vehicleId(vehicleEntity.getVehicleId())
                .make(vehicleEntity.getMake())
                .createdAt(vehicleEntity.getCreatedAt())
                .vehicleStatus(vehicleEntity.getVehicleStatus())
                .lastServiceMileage(vehicleEntity.getLastServiceMileage())
                .plateNumber(vehicleEntity.getPlateNumber())
                .model(vehicleEntity.getModel())
                .callSign(vehicleEntity.getCallSign())
                .securityCompanyId(vehicleEntity.getSecurityCompanyEntity().getSecurityCompanyId())
                .zoneId(vehicleEntity.getZoneEntity().getZoneId())
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
