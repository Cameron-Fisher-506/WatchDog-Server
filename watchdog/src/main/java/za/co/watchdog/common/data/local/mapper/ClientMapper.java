package za.co.watchdog.common.data.local.mapper;

import za.co.watchdog.common.data.local.database.model.*;
import za.co.watchdog.features.clientManagement.domain.model.*;

public class ClientMapper {
    public static Client mapToClient(ClientEntity clientEntity) {
        return new Client(
                clientEntity.getClientId(),
                mapToHub(clientEntity.getHubEntity()),
                clientEntity.getName(),
                clientEntity.getSurname(),
                clientEntity.getContactNumber(),
                clientEntity.getEmailAddress(),
                clientEntity.getPasswordHash(),
                mapToAddress(clientEntity.getAddressEntity()),
                mapToLocation(clientEntity.getLocationEntity())
        );
    }

    private static Hub mapToHub(HubEntity hubEntity) {
        return new Hub(
                hubEntity.getHubId(),
                mapToSenor(hubEntity.getSensorEntity()),
                hubEntity.getMacAddress(),
                hubEntity.getStatus(),
                hubEntity.getLastHeartbeat()
        );
    }

    private static Address mapToAddress(AddressEntity addressEntity) {
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

    private static Location mapToLocation(LocationEntity locationEntity) {
        return new Location(
                locationEntity.getLocationId(),
                locationEntity.getLatitude(),
                locationEntity.getLongitude()
        );
    }

    private static Sensor mapToSenor(SensorEntity sensorEntity) {
        return new Sensor(
                sensorEntity.getSensorId(),
                sensorEntity.getType(),
                sensorEntity.getZoneName()
        );
    }

    public static ClientEntity mapToClientEntity(Client client) {
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

    private static HubEntity mapToHubEntity(Hub hub) {
        return new HubEntity(
                hub.hubId(),
                mapToSenorEntity(hub.sensor()),
                hub.macAddress(),
                hub.status(),
                hub.lastHeartbeat()
        );
    }

    private static AddressEntity mapToAddressEntity(Address address) {
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

    private static LocationEntity mapToLocationEntity(Location location) {
        return new LocationEntity(
                location.locationId(),
                location.latitude(),
                location.longitude()
        );
    }

    private static SensorEntity mapToSenorEntity(Sensor sensor) {
        return new SensorEntity(
                sensor.sensorId(),
                sensor.type(),
                sensor.zoneName()
        );
    }
}
