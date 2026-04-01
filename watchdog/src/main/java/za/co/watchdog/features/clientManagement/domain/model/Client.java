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
    public Client copyWith(String passwordHash) {
        return new Client(this.clientId, this.hub, this.name, this.surname, this.contactNumber, this.address, this.location, this.user.copyWith(passwordHash));
    }
}
