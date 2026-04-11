package za.co.watchdog.features.clientManagement.presentation.mapper;

import org.springframework.stereotype.Component;
import za.co.watchdog.common.domain.model.*;
import za.co.watchdog.common.presentation.model.*;
import za.co.watchdog.common.presentation.model.UserRole;
import za.co.watchdog.features.clientManagement.presentation.model.clientOnboarding.ClientOnboardingRequestDto;

@Component
public class ClientPresenterMapper {
    public Client mapToClient(ClientOnboardingRequestDto clientOnboardingRequestDto) {
        return Client.builder()
                .contactNumber(clientOnboardingRequestDto.getContactNumber())
                .name(clientOnboardingRequestDto.getName())
                .surname(clientOnboardingRequestDto.getSurname())
                .userId(clientOnboardingRequestDto.getUserId())
                .build();
    }

    private Address mapToAddress(AddressDto addressDto) {
        return Address.builder()
                .addressLineOne(addressDto.addressLineOne())
                .addressLineTwo(addressDto.addressLineTwo())
                .suburb(addressDto.suburb())
                .postalCode(addressDto.postalCode())
                .build();
    }

    private Location mapToLocation(LocationDto locationDto) {
        return Location.builder()
                .longitude(locationDto.longitude())
                .latitude(locationDto.latitude())
                .address(mapToAddress(locationDto.addressDto()))
                .build();
    }

    private Sensor mapToSenor(SensorDto sensorDto) {
        return Sensor.builder()
                .zoneName(sensorDto.zoneName())
                .type(sensorDto.type())
                .build();
    }

    private User mapToUser(UserDto userDto) {
        return User.builder()
                .userId(userDto.getUserId())
                .emailAddress(userDto.getEmailAddress())
                .createdAt(userDto.getCreatedAt())
                .userRole(mapToUserRole(userDto.getUserRole() != null ? userDto.getUserRole() : UserRole.UNKNOWN))
                .build();
    }

    private UserRole mapToUserRole(za.co.watchdog.common.domain.model.UserRole userRole) {
        return UserRole.valueOf(userRole.name());
    }

    private za.co.watchdog.common.domain.model.UserRole mapToUserRole(UserRole userRole) {
        return za.co.watchdog.common.domain.model.UserRole.valueOf(userRole.name());
    }
}
