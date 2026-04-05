package za.co.watchdog.features.clientManagement.presentation.mapper;

import org.springframework.stereotype.Component;
import za.co.watchdog.common.domain.model.*;
import za.co.watchdog.features.authManagement.domain.model.*;
import za.co.watchdog.features.clientManagement.presentation.model.clientOnboarding.ClientOnboardingRequestDto;
import za.co.watchdog.features.clientManagement.presentation.model.clientOnboarding.dto.*;
import za.co.watchdog.features.clientManagement.presentation.model.clientOnboarding.dto.UserRole;

@Component
public class ClientPresenterMapper {
    public Client mapToClient(ClientOnboardingRequestDto clientOnboardingRequestDto) {
        return Client.builder()
                .userId(clientOnboardingRequestDto.getUserDto().getUserId())
                .hub(mapToHub(clientOnboardingRequestDto.getHubDto()))
                .address(mapToAddress(clientOnboardingRequestDto.getAddressDto()))
                .createdAt(clientOnboardingRequestDto.getUserDto().getCreatedAt())
                .emailAddress(clientOnboardingRequestDto.getUserDto().getEmailAddress())
                .password(clientOnboardingRequestDto.getUserDto().getPassword())
                .isActive(clientOnboardingRequestDto.getUserDto().getIsActive())
                .contactNumber(clientOnboardingRequestDto.getContactNumber())
                .location(mapToLocation(clientOnboardingRequestDto.getLocationDto()))
                .name(clientOnboardingRequestDto.getName())
                .surname(clientOnboardingRequestDto.getSurname())
                .userRole(mapToUserRole(clientOnboardingRequestDto.getUserDto().getUserRole()))
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

    private UserRole mapToUserRole(za.co.watchdog.common.domain.model.UserRole userRole) {
        return UserRole.valueOf(userRole.name());
    }

    private za.co.watchdog.common.domain.model.UserRole mapToUserRole(UserRole userRole) {
        return za.co.watchdog.common.domain.model.UserRole.valueOf(userRole.name());
    }
}
