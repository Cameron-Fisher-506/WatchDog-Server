package za.co.watchdog.common.presentation.model;

import lombok.*;
import lombok.experimental.SuperBuilder;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@SuperBuilder
public abstract class ResponseDto {
    protected String title;
    protected String message;
}
