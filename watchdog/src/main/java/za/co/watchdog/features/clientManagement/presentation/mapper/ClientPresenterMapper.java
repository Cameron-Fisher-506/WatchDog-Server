package za.co.watchdog.features.clientManagement.presentation.mapper;

import org.springframework.stereotype.Component;
import za.co.watchdog.common.domain.model.*;
import za.co.watchdog.common.presentation.model.*;
import za.co.watchdog.features.clientManagement.domain.model.Address;
import za.co.watchdog.features.clientManagement.domain.model.Location;
import za.co.watchdog.features.clientManagement.presentation.model.clientOnboarding.ClientOnboardingRequestDto;
import za.co.watchdog.features.incidentManagement.domain.model.Client;

@Component
public class ClientPresenterMapper {
    public Client mapToClient(ClientOnboardingRequestDto clientOnboardingRequestDto) {
        return Client.builder()
                .contactNumber(clientOnboardingRequestDto.getContactNumber())
                .name(clientOnboardingRequestDto.getName())
                .surname(clientOnboardingRequestDto.getSurname())
                .userId(clientOnboardingRequestDto.getUserDto().getUserId())
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
                .userRole(userDto.getUserRole())
                .build();
    }
}
