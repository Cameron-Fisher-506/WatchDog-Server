package za.co.watchdog.features.clientManagement.presentation.mapper;

import org.springframework.stereotype.Component;
import za.co.watchdog.features.authManagement.domain.model.*;
import za.co.watchdog.features.authManagement.presentation.model.clientOnboarding.ClientRequestDto;
import za.co.watchdog.features.clientManagement.presentation.model.clientOnboarding.dto.*;
import za.co.watchdog.features.clientManagement.presentation.model.clientOnboarding.dto.UserRole;

@Component
public class ClientPresenterMapper {
    public Client mapToClient(ClientRequestDto clientRequestDto) {
        return Client.builder()
                .userId(clientRequestDto.getUserId())
                .hub(mapToHub(clientRequestDto.getHubDto()))
                .address(mapToAddress(clientRequestDto.getAddressDto()))
                .createdAt(clientRequestDto.getCreatedAt())
                .emailAddress(clientRequestDto.getEmailAddress())
                .password(clientRequestDto.getPassword())
                .isActive(clientRequestDto.getIsActive())
                .contactNumber(clientRequestDto.getContactNumber())
                .location(mapToLocation(clientRequestDto.getLocationDto()))
                .name(clientRequestDto.getName())
                .surname(clientRequestDto.getSurname())
                .userRole(mapToUserRole(clientRequestDto.getUserRole()))
                .build();
    }

    private Hub mapToHub(HubDto hubDto) {
        return Hub.builder()
                .sensor(mapToSenor(hubDto.sensorDto()))
                .status(hubDto.status())
                .lastHeartbeat(hubDto.lastHeartbeat())
                .macAddress(hubDto.macAddress())
                .build();
    }

    private Address mapToAddress(AddressDto addressDto) {
        return Address.builder()
                .addressLineOne(addressDto.addressLineOne())
                .addressLineTwo(addressDto.addressLineTwo())
                .longitude(addressDto.longitude())
                .latitude(addressDto.latitude())
                .suburb(addressDto.suburb())
                .postalCode(addressDto.postalCode())
                .build();
    }

    private Location mapToLocation(LocationDto locationDto) {
        return Location.builder()
                .longitude(locationDto.longitude())
                .latitude(locationDto.latitude())
                .build();
    }

    private Sensor mapToSenor(SensorDto sensorDto) {
        return Sensor.builder()
                .zoneName(sensorDto.zoneName())
                .type(sensorDto.type())
                .build();
    }

    private UserRole mapToUserRole(za.co.watchdog.features.authManagement.domain.model.UserRole userRole) {
        return UserRole.valueOf(userRole.name());
    }

    private za.co.watchdog.features.authManagement.domain.model.UserRole mapToUserRole(UserRole userRole) {
        return za.co.watchdog.features.authManagement.domain.model.UserRole.valueOf(userRole.name());
    }
}
