package za.co.watchdog.features.authManagement.presentation.mapper;

import org.springframework.stereotype.Component;
import za.co.watchdog.features.authManagement.domain.model.*;
import za.co.watchdog.features.authManagement.presentation.model.auth.AuthResponseDto;
import za.co.watchdog.features.authManagement.presentation.model.client.ClientRequestDto;
import za.co.watchdog.features.authManagement.presentation.model.client.dto.*;

@Component
public class ClientMapper {
    public Client mapToClient(ClientRequestDto clientRequestDto) {
        return Client.builder()
                .userId(clientRequestDto.clientId())
                .hub(mapToHub(clientRequestDto.hubDto()))
                .address(mapToAddress(clientRequestDto.addressDto()))
                .createdAt(clientRequestDto.userDto().createdAt())
                .emailAddress(clientRequestDto.userDto().emailAddress())
                .password(clientRequestDto.userDto().password())
                .isActive(clientRequestDto.userDto().isActive())
                .contactNumber(clientRequestDto.contactNumber())
                .location(mapToLocation(clientRequestDto.locationDto()))
                .name(clientRequestDto.name())
                .surname(clientRequestDto.surname())
                .userRole(mapToUserRole(clientRequestDto.userDto().userRole()))
                .build();
    }

    private Hub mapToHub(HubDto hubDto) {
        return new Hub(
                hubDto.hubId(),
                mapToSenor(hubDto.sensorDto()),
                hubDto.macAddress(),
                hubDto.status(),
                hubDto.lastHeartbeat()
        );
    }

    private Address mapToAddress(AddressDto addressDto) {
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

    private Location mapToLocation(LocationDto locationDto) {
        return new Location(
                locationDto.locationId(),
                locationDto.latitude(),
                locationDto.longitude()
        );
    }

    private Sensor mapToSenor(SensorDto sensorDto) {
        return new Sensor(
                sensorDto.sensorId(),
                sensorDto.type(),
                sensorDto.zoneName()
        );
    }

    private za.co.watchdog.features.authManagement.presentation.model.client.dto.UserRole mapToUserRole(za.co.watchdog.features.authManagement.domain.model.UserRole userRole) {
        return za.co.watchdog.features.authManagement.presentation.model.client.dto.UserRole.valueOf(userRole.name());
    }

    private za.co.watchdog.features.authManagement.domain.model.UserRole mapToUserRole(za.co.watchdog.features.authManagement.presentation.model.client.dto.UserRole userRole) {
        return za.co.watchdog.features.authManagement.domain.model.UserRole.valueOf(userRole.name());
    }
}
