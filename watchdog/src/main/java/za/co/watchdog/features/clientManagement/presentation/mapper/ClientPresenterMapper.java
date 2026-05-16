package za.co.watchdog.features.clientManagement.presentation.mapper;

import org.springframework.stereotype.Component;
import za.co.watchdog.common.domain.model.*;
import za.co.watchdog.features.clientManagement.domain.model.Address;
import za.co.watchdog.features.clientManagement.presentation.model.clientOnboarding.ClientOnboardingRequestDto;
import za.co.watchdog.features.clientManagement.domain.model.Client;
import za.co.watchdog.features.clientManagement.presentation.model.clientOnboarding.dto.AddressDto;
import za.co.watchdog.features.clientManagement.presentation.model.clientOnboarding.dto.LocationDto;
import za.co.watchdog.features.clientManagement.presentation.model.clientOnboarding.dto.SensorDto;
import za.co.watchdog.features.clientManagement.presentation.model.updateProfileInformation.UpdateProfileInformationRequestDto;

@Component
public class ClientPresenterMapper {
    public Client mapToClient(ClientOnboardingRequestDto clientOnboardingRequestDto, Long userId) {
        return Client.builder()
                .contactNumber(clientOnboardingRequestDto.getContactNumber())
                .name(clientOnboardingRequestDto.getName())
                .surname(clientOnboardingRequestDto.getSurname())
                .userId(userId)
                .build();
    }

    public Client mapToClient(UpdateProfileInformationRequestDto updateProfileInformationRequestDto, Long userId) {
        return Client.builder()
                .contactNumber(updateProfileInformationRequestDto.getContactNumber())
                .name(updateProfileInformationRequestDto.getName())
                .surname(updateProfileInformationRequestDto.getSurname())
                .userId(userId)
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

    public Address mapToAddress(AddressDto addressDto, Long clientId) {
        return Address.builder()
                .latitude(addressDto.latitude())
                .longitude(addressDto.longitude())
                .suburb(addressDto.suburb())
                .addressLineTwo(addressDto.addressLineOne())
                .addressLineTwo(addressDto.addressLineTwo())
                .postalCode(addressDto.postalCode())
                .clientId(clientId)
                .zoneId(addressDto.zoneId())
                .build();
    }

    private Sensor mapToSenor(SensorDto sensorDto) {
        return Sensor.builder()
                .zoneName(sensorDto.zoneName())
                .type(sensorDto.type())
                .build();
    }
}
