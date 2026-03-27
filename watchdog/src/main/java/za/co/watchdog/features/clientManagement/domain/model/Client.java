package za.co.watchdog.features.clientManagement.domain.model;

public record Client(
        Long clientId,
        Hub hub,
        String name,
        String surname,
        String contactNumber,
        Address address,
        Location location,
        User user
) {

}
