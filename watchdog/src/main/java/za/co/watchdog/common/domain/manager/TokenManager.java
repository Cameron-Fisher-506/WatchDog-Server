package za.co.watchdog.common.domain.manager;

public interface TokenManager {
    String generateToken(String username, String role);
    String extractUsername(String token);
    Boolean isTokenValid(String token, String username);
    String extractRole(String token);
}
