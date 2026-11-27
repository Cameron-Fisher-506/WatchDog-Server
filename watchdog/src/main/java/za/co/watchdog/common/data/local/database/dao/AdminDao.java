package za.co.watchdog.common.data.local.database.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import za.co.watchdog.common.data.local.database.model.AdminEntity;

import java.util.Optional;

@Repository
public interface AdminDao extends JpaRepository<AdminEntity, Long> {
    @Query("SELECT adminEntity " +
            " FROM AdminEntity adminEntity" +
            " WHERE adminEntity.userEntity.userId = :userId")
    Optional<AdminEntity> findByUserId(Long userId);
}
