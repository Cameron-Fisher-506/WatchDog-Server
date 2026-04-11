package za.co.watchdog.common.domain.model.oneTimePin;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Builder
public class ValidateOneTimePin {
    private Long userId;
    private String oneTimePin;
}
