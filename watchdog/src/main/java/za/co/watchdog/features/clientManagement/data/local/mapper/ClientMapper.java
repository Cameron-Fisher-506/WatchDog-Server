package za.co.watchdog.features.clientManagement.data.local.mapper;

import org.springframework.stereotype.Component;
import za.co.watchdog.common.data.local.database.model.*;
import za.co.watchdog.common.data.local.database.model.UserRole;
import za.co.watchdog.features.clientManagement.domain.model.*;

@Component
public class ClientMapper {

    public Client mapToClient(ClientEntity clientEntity) {
        return Client.builder()
                .userId(clientEntity.getUserId())
                .name(clientEntity.getName())
                .surname(clientEntity.getSurname())
                .address(mapToAddress(clientEntity.getAddressEntity()))
                .location(mapToLocation(clientEntity.getLocationEntity()))
                .contactNumber(clientEntity.getContactNumber())
                .hub(mapToHub(clientEntity.getHubEntity()))
                .emailAddress(clientEntity.getEmailAddress())
                .password(clientEntity.getPasswordHash())
                .userRole(mapToUserRole(clientEntity.getUserRole()))
                .isActive(clientEntity.getIsActive())
                .createdAt(clientEntity.getCreatedAt())
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

    public ClientEntity mapToClientEntity(Client client) {
        return ClientEntity.builder()
                .userId(client.getUserId())
                .name(client.getName())
                .surname(client.getSurname())
                .addressEntity(mapToAddressEntity(client.getAddress()))
                .locationEntity(mapToLocationEntity(client.getLocation()))
                .contactNumber(client.getContactNumber())
                .hubEntity(mapToHubEntity(client.getHub()))
                .emailAddress(client.getEmailAddress())
                .passwordHash(client.getPassword())
                .userRole(mapToUserRole(client.getUserRole()))
                .isActive(client.getIsActive())
                .createdAt(client.getCreatedAt())
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

    private UserRole mapToUserRole(za.co.watchdog.features.clientManagement.domain.model.UserRole userRole) {
        return za.co.watchdog.common.data.local.database.model.UserRole.valueOf(userRole.name());
    }

    private za.co.watchdog.features.clientManagement.domain.model.UserRole mapToUserRole(UserRole userRole) {
        return za.co.watchdog.features.clientManagement.domain.model.UserRole.valueOf(userRole.name());
    }
}
