package za.co.watchdog.common.domain.model;

import lombok.Getter;
import lombok.experimental.SuperBuilder;


@Getter
@SuperBuilder
public class Client extends User {
    private Hub hub;
    private String name;
    private String surname;
    private String contactNumber;
    private Address address;
    private Location location;

    public Client copyWith(String passwordHash) {
        return Client.builder()
                .hub(this.hub)
                .userId(this.userId)
                .emailAddress(this.emailAddress)
                .password(passwordHash)
                .isActive(this.isActive)
                .createdAt(this.createdAt)
                .surname(this.surname)
                .name(this.name)
                .surname(this.surname)
                .contactNumber(this.contactNumber)
                .userRole(this.userRole)
                .location(this.location)
                .address(this.address)
                .build();
    }
}
