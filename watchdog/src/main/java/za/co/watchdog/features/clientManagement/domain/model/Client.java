package za.co.watchdog.features.clientManagement.domain.model;

public record Client(
        Long clientId,
        Hub hub,
        String name,
        String surname,
        String contactNumber,
        String emailAddress,
        String passwordHash,
        Address address,
        Location location
) {

}
