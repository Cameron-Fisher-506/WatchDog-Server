package za.co.watchdog.features.authManagement.presentation.model.client;

import jakarta.annotation.security.DenyAll;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;
import za.co.watchdog.features.authManagement.presentation.model.client.dto.AddressDto;
import za.co.watchdog.features.authManagement.presentation.model.client.dto.HubDto;
import za.co.watchdog.features.authManagement.presentation.model.client.dto.LocationDto;
import za.co.watchdog.features.authManagement.presentation.model.client.dto.UserDto;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@SuperBuilder
public class ClientRequestDto extends UserDto {
    HubDto hubDto;
    String name;
    String surname;
    String contactNumber;
    AddressDto addressDto;
    LocationDto locationDto;
}