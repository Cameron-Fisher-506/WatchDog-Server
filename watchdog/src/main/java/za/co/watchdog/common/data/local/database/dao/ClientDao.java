package za.co.watchdog.common.data.local.database.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import za.co.watchdog.common.data.local.database.model.ClientEntity;

import java.util.Optional;

@Repository
public interface ClientDao extends JpaRepository<ClientEntity, Long> {

    Optional<ClientEntity> findByEmailAddress(String emailAddress);
}
