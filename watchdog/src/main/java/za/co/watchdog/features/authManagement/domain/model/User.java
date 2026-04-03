
package za.co.watchdog.features.authManagement.domain.model;

import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import lombok.Data;
import lombok.Getter;
import lombok.experimental.SuperBuilder;

import java.time.Instant;

@Data
@Getter
@SuperBuilder
public abstract class User {
        protected Long userId;
        protected String emailAddress;
        protected String password;
        @Enumerated(EnumType.STRING)
        protected UserRole userRole;
        protected Boolean isActive;
        protected Instant createdAt;
}
