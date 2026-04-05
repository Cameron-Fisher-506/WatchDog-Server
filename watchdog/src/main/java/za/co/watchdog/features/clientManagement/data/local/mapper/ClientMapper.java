package za.co.watchdog.features.clientManagement.data.local.mapper;

import org.springframework.stereotype.Component;
import za.co.watchdog.common.data.local.database.model.*;
import za.co.watchdog.common.data.local.database.model.UserRole;
import za.co.watchdog.common.domain.model.*;

@Component
public class ClientMapper {

    public Client mapToClient(ClientEntity clientEntity) {
        return Client.builder()
                .name(clientEntity.getName())
                .surname(clientEntity.getSurname())
                .address(mapToAddress(clientEntity.getAddressEntity()))
                .location(mapToLocation(clientEntity.getLocationEntity()))
                .contactNumber(clientEntity.getContactNumber())
                .hub(mapToHub(clientEntity.getHubEntity()))
                .user(mapToUser(clientEntity.getUserEntity()))
                .build();
    }

    private Hub mapToHub(HubEntity hubEntity) {
        return new Hub(
                hubEntity.getHubId(),
                mapToSenor(hubEntity.getSensorEntity()),
                hubEntity.getMacAddress(),
                hubEntity.getStatus(),
                hubEntity.getLastHeartbeat()
        );
    }

    private Address mapToAddress(AddressEntity addressEntity) {
        return new Address(
                addressEntity.getAddressId(),
                addressEntity.getLatitude(),
                addressEntity.getLongitude(),
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
                locationEntity.getLongitude()
        );
    }

    private Sensor mapToSenor(SensorEntity sensorEntity) {
        return new Sensor(
                sensorEntity.getSensorId(),
                sensorEntity.getType(),
                sensorEntity.getZoneName()
        );
    }

    private User mapToUser(UserEntity userEntity) {
        return User.builder()
                .userId(userEntity.getUserId())
                .emailAddress(userEntity.getEmailAddress())
                .createdAt(userEntity.getCreatedAt())
                .isActive(userEntity.getIsActive())
                .userRole(mapToUserRole(userEntity.getUserRole()))
                .build();
    }

    public ClientEntity mapToClientEntity(Client client) {
        return ClientEntity.builder()
                .name(client.getName())
                .surname(client.getSurname())
                .addressEntity(mapToAddressEntity(client.getAddress()))
                .locationEntity(mapToLocationEntity(client.getLocation()))
                .contactNumber(client.getContactNumber())
                .hubEntity(mapToHubEntity(client.getHub()))
                .userEntity(mapToUserE
                )
                .build();
    }

    private HubEntity mapToHubEntity(Hub hub) {
        return new HubEntity(
                hub.hubId(),
                mapToSenorEntity(hub.sensor()),
                hub.macAddress(),
                hub.status(),
                hub.lastHeartbeat()
        );
    }

    private AddressEntity mapToAddressEntity(Address address) {
        return new AddressEntity(
                address.addressId(),
                address.latitude(),
                address.longitude(),
                address.addressLineOne(),
                address.addressLineTwo(),
                address.suburb(),
                address.postalCode()
        );
    }

    private LocationEntity mapToLocationEntity(Location location) {
        return new LocationEntity(
                location.locationId(),
                location.latitude(),
                location.longitude()
        );
    }

    private SensorEntity mapToSenorEntity(Sensor sensor) {
        return new SensorEntity(
                sensor.sensorId(),
                sensor.type(),
                sensor.zoneName()
        );
    }

    private User mapToUserEntity(User user) {
        return User.builder()
                .userId(user.getUserId())
                .emailAddress(user.getEmailAddress())
                .createdAt(user.getCreatedAt())
                .isActive(user.getIsActive())
                .userRole(mapToUserRole(user.getUserRole()))
                .build();
    }

    private UserRole mapToUserRole(za.co.watchdog.common.domain.model.UserRole userRole) {
        return UserRole.valueOf(userRole.name());
    }

    private za.co.watchdog.common.domain.model.UserRole mapToDataUserRole(UserRole userRole) {
        return za.co.watchdog.common.domain.model.UserRole.valueOf(userRole.name());
    }
}
