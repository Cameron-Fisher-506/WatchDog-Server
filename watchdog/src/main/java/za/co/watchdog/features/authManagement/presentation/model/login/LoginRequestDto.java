package za.co.watchdog.features.authManagement.presentation.model.login;

import lombok.Builder;

@Builder
public class LoginRequestDto {
    public String emailAddress;
    public String password;
}
