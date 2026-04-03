package za.co.watchdog.features.authManagement.domain.model;

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
}
