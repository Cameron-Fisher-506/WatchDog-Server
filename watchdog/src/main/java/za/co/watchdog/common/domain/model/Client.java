package za.co.watchdog.common.domain.model;

import lombok.Builder;
import lombok.Data;
import lombok.Getter;
import lombok.experimental.SuperBuilder;

@Data
@Getter
@Builder
public class Client {
    private Long clientId;
    private Hub hub;
    private String name;
    private String surname;
    private String contactNumber;
    private Address address;
    private Location location;
    private User user;

    public Client copyWith(User user) {
        return Client.builder()
                .clientId(this.getClientId())
                .hub(this.getHub())
                .user(user)
                .name(this.getName())
                .surname(this.getSurname())
                .contactNumber(this.getContactNumber())
                .address(this.getAddress())
                .location(this.getLocation())
                .build();
    }
}
