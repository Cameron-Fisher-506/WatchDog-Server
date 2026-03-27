package za.co.watchdog.features.clientManagement.presentation.mapper;

import za.co.watchdog.common.data.local.database.model.UserRole;
import za.co.watchdog.features.clientManagement.domain.model.*;
import za.co.watchdog.features.clientManagement.presentation.model.client.ClientRequestDto;
import za.co.watchdog.features.clientManagement.presentation.model.client.ClientResponseDto;
import za.co.watchdog.features.clientManagement.presentation.model.client.dto.*;

public class ClientMapper {
    public static Client mapToClient(ClientRequestDto clientDto) {
        return new Client(
                clientDto.clientId(),
                mapToHub(clientDto.hubDto()),
                clientDto.name(),
                clientDto.surname(),
                clientDto.contactNumber(),
                mapToAddress(clientDto.addressDto()),
                mapToLocation(clientDto.locationDto()),
                mapToUser(clientDto.userDto())
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

    private static User mapToUser(UserDto userDto) {
        return new User(
                userDto.userId(),
                userDto.emailAddress(),
                userDto.password(),
                mapToUserRole(userDto.userRole()),
                userDto.isActive(),
                userDto.createdAt()
        );
    }

    private static za.co.watchdog.features.clientManagement.presentation.model.client.dto.UserRole mapToUserRole(za.co.watchdog.features.clientManagement.domain.model.UserRole userRole) {
        return za.co.watchdog.features.clientManagement.presentation.model.client.dto.UserRole.valueOf(userRole.name());
    }

    private static  za.co.watchdog.features.clientManagement.domain.model.UserRole mapToUserRole(za.co.watchdog.features.clientManagement.presentation.model.client.dto.UserRole userRole) {
        return za.co.watchdog.features.clientManagement.domain.model.UserRole.valueOf(userRole.name());
    }

    public static ClientResponseDto mapToClientResponseDto(Client client, String token) {
        return new ClientResponseDto(
                client.clientId(),
                token
        );
    }
}
