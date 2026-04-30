package za.co.watchdog.features.clientManagement.domain.model;

import lombok.Builder;
import lombok.Data;
import lombok.Getter;

@Data
@Getter
@Builder
public class Client {
    private Long clientId;
    private String name;
    private String surname;
    private String contactNumber;
    private Long userId;
    private Long securityCompanyId;

    public Client copyWith(Long userId) {
        return Client.builder()
                .clientId(getClientId())
                .userId(userId)
                .name(getName())
                .surname(getSurname())
                .contactNumber(getContactNumber())
                .securityCompanyId(getSecurityCompanyId())
                .build();
    }
}
