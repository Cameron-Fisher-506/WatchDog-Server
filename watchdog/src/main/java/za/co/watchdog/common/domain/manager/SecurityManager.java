package za.co.watchdog.common.domain.manager;

public interface SecurityManager {
    Boolean passwordMatches(String passwordOne, String passwordTwo);
    String encode(String rawPassword);
    String getCurrentUsername();
}
