package za.co.watchdog.common.data.local.database.model;

import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.SuperBuilder;

import java.time.Instant;

@Data
@NoArgsConstructor
@AllArgsConstructor
@SuperBuilder
@Entity
@Table(name = "app_user")
@Inheritance(strategy = InheritanceType.JOINED)
public abstract class UserEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    protected Long userId;
    protected String emailAddress;
    protected String passwordHash;
    @Enumerated(EnumType.STRING)
    protected UserRole userRole;
    protected Boolean isActive;
    protected Instant createdAt;
}
