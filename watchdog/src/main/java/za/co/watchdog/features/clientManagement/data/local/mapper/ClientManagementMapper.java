package za.co.watchdog.features.clientManagement.data.local.mapper;

import jakarta.persistence.EntityManager;
import org.springframework.stereotype.Component;
import za.co.watchdog.common.data.local.database.model.*;
import za.co.watchdog.features.clientManagement.domain.model.Address;
import za.co.watchdog.features.clientManagement.domain.model.Location;
import za.co.watchdog.features.clientManagement.domain.model.Vehicle;

@Component
public class ClientManagementMapper {
    private final EntityManager entityManager;

    ClientManagementMapper(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    public Location mapToLocation(LocationEntity locationEntity) {
        return Location.builder()
                .locationId(locationEntity.getLocationId())
                .longitude(locationEntity.getLongitude())
                .latitude(locationEntity.getLatitude())
                .clientId(locationEntity.getClientEntity().getClientId())
                .vehicleId(locationEntity.getVehicleEntity().getVehicleId())
                .build();
    }

    public Address mapToAddress(AddressEntity addressEntity) {
        return Address.builder()
                .addressLineOne(addressEntity.getAddressLineOne())
                .addressLineTwo(addressEntity.getAddressLineTwo())
                .suburb(addressEntity.getSuburb())
                .postalCode(addressEntity.getPostalCode())
                .build();
    }

    public LocationEntity mapToLocationEntity(Location location) {
        return LocationEntity.builder()
                .locationId(location.getLocationId())
                .longitude(location.getLongitude())
                .latitude(location.getLatitude())
                .clientEntity(entityManager.getReference(ClientEntity.class, location.getClientId()))
                .vehicleEntity(entityManager.getReference(VehicleEntity.class, location.getVehicleId()))
                .build();
    }

    public AddressEntity mapToAddressEntity(Address address) {
        return AddressEntity.builder()
                .latitude(address.latitude())
                .longitude(address.longitude())
                .addressLineOne(address.addressLineOne())
                .addressLineTwo(address.addressLineTwo())
                .suburb(address.suburb())
                .postalCode(address.postalCode())
                .build();
    }

    public Vehicle mapToVehicle(VehicleEntity vehicleEntity) {
        return Vehicle.builder()
                .vehicleId(vehicleEntity.getVehicleId())
                .make(vehicleEntity.getMake())
                .createdAt(vehicleEntity.getCreatedAt())
                .vehicleStatus(vehicleEntity.getVehicleStatus())
                .lastServiceMileage(vehicleEntity.getLastServiceMileage())
                .plateNumber(vehicleEntity.getPlateNumber())
                .model(vehicleEntity.getModel())
                .callSign(vehicleEntity.getCallSign())
                .securityCompanyId(vehicleEntity.getSecurityCompanyEntity().getSecurityCompanyId())
                .zoneId(vehicleEntity.getZoneEntity().getZoneId())
                .build();
    }
}
