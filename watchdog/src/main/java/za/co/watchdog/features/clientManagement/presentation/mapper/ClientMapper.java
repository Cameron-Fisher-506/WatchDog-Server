package za.co.watchdog.features.clientManagement.presentation.mapper;

import za.co.watchdog.common.data.local.database.model.*;
import za.co.watchdog.features.clientManagement.domain.model.*;
import za.co.watchdog.features.clientManagement.presentation.model.*;

public class ClientMapper {
    public static Client mapToClient(ClientDto clientDto) {
        return new Client(
                clientDto.clientId(),
                mapToHub(clientDto.hubDto()),
                clientDto.name(),
                clientDto.surname(),
                clientDto.contactNumber(),
                clientDto.emailAddress(),
                clientDto.passwordHash(),
                mapToAddress(clientDto.addressDto()),
                mapToLocation(clientDto.locationDto())
        );
    }

    private static Hub mapToHub(HubDto hubDto) {
        return new Hub(
                hubDto.hubId(),
                mapToSenor(hubDto.sensorDto()),
                hubDto.macAddress(),
                hubDto.status(),
                hubDto.lastHeartbeat()
        );
    }

    private static Address mapToAddress(AddressDto addressDto) {
        return new Address(
                addressDto.addressId(),
                addressDto.latitude(),
                addressDto.longitude(),
                addressDto.addressLineOne(),
                addressDto.addressLineTwo(),
                addressDto.suburb(),
                addressDto.postalCode()
        );
    }

    private static Location mapToLocation(LocationDto locationDto) {
        return new Location(
                locationDto.locationId(),
                locationDto.latitude(),
                locationDto.longitude()
        );
    }

    private static Sensor mapToSenor(SensorDto sensorDto) {
        return new Sensor(
                sensorDto.sensorId(),
                sensorDto.type(),
                sensorDto.zoneName()
        );
    }

    public static ClientDto mapToClientDto(Client client) {
        return new ClientDto(
                client.clientId(),
                mapToHubDto(client.hub()),
                client.name(),
                client.surname(),
                client.contactNumber(),
                client.emailAddress(),
                client.passwordHash(),
                mapToAddressDto(client.address()),
                mapToLocationDto(client.location())
        );
    }

    private static HubDto mapToHubDto(Hub hub) {
        return new HubDto(
                hub.hubId(),
                mapToSenorDto(hub.sensor()),
                hub.macAddress(),
                hub.status(),
                hub.lastHeartbeat()
        );
    }

    private static AddressDto mapToAddressDto(Address address) {
        return new AddressDto(
                address.addressId(),
                address.latitude(),
                address.longitude(),
                address.addressLineOne(),
                address.addressLineTwo(),
                address.suburb(),
                address.postalCode()
        );
    }

    private static LocationDto mapToLocationDto(Location location) {
        return new LocationDto(
                location.locationId(),
                location.latitude(),
                location.longitude()
        );
    }

    private static SensorDto mapToSenorDto(Sensor sensor) {
        return new SensorDto(
                sensor.sensorId(),
                sensor.type(),
                sensor.zoneName()
        );
    }
}
