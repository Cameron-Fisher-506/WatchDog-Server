package za.co.watchdog.common.data.local.database.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import za.co.watchdog.common.data.local.database.model.DeviceEntity;
import za.co.watchdog.common.data.local.database.model.UserEntity;

import java.util.Optional;

@Repository
public interface DeviceDao extends JpaRepository<DeviceEntity, Long> {
    Optional<DeviceEntity> findByDeviceFingerprint(String deviceFingerprint);
    @Query("SELECT deviceEntity FROM DeviceEntity deviceEntity WHERE deviceEntity.userEntity.userId = :userId")
    Optional<DeviceEntity> findByUserId(Long userId);
}
