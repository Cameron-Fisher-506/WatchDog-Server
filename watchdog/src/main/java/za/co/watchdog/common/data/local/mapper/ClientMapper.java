package za.co.watchdog.common.data.local.mapper;

import org.springframework.stereotype.Component;
import za.co.watchdog.common.data.local.database.model.*;
import za.co.watchdog.common.util.SecurityConfig;
import za.co.watchdog.features.clientManagement.domain.model.*;

@Component
public class ClientMapper {
    private final SecurityConfig securityConfig;

    public ClientMapper(SecurityConfig securityConfig) {
        this.securityConfig = securityConfig;
    }

    public Client mapToClient(ClientEntity clientEntity) {
        return new Client(
                clientEntity.getClientId(),
                mapToHub(clientEntity.getHubEntity()),
                clientEntity.getName(),
                clientEntity.getSurname(),
                clientEntity.getContactNumber(),
                clientEntity.getEmailAddress(),
                securityConfig.passwordEncoder().encode(clientEntity.getPasswordHash()),
                mapToAddress(clientEntity.getAddressEntity()),
                mapToLocation(clientEntity.getLocationEntity())
        );
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
        return new ClientEntity(
                client.clientId(),
                mapToHubEntity(client.hub()),
                client.name(),
                client.surname(),
                client.contactNumber(),
                client.emailAddress(),
                client.passwordHash(),
                mapToAddressEntity(client.address()),
                mapToLocationEntity(client.location())
        );
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
}
