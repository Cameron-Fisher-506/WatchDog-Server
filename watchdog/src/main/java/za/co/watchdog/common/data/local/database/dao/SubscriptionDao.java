package za.co.watchdog.common.data.local.database.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import za.co.watchdog.common.data.local.database.model.SubscriptionEntity;

@Repository
public interface SubscriptionDao extends JpaRepository<SubscriptionEntity, Long> {
}
