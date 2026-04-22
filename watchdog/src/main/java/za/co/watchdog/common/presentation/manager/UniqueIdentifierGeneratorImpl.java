package za.co.watchdog.common.presentation.manager;

import org.springframework.stereotype.Component;
import za.co.watchdog.common.domain.manager.UniqueIdentifierGenerator;

import java.util.UUID;

@Component
public class UniqueIdentifierGeneratorImpl implements UniqueIdentifierGenerator {
    @Override
    public String generate() {
        return UUID.randomUUID().toString();
    }
}
