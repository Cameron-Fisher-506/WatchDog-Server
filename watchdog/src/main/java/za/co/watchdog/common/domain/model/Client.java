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
    private String name;
    private String surname;
    private String contactNumber;
    private Long userId;

    public Client copyWith(Long userId) {
        return Client.builder()
                .clientId(getClientId())
                .userId(userId)
                .name(getName())
                .surname(getSurname())
                .contactNumber(getContactNumber())
                .build();
    }
}
