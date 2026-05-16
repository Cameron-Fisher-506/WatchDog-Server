package za.co.watchdog.features.clientManagement.presentation.model.updateProfileInformation;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import tools.jackson.databind.annotation.JsonDeserialize;
import za.co.watchdog.common.presentation.converters.StringToInstantConverter;

import java.time.Instant;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Builder
public class UpdateProfileInformationRequestDto {
    private String name;
    private String surname;
    private String contactNumber;
    @JsonDeserialize(converter = StringToInstantConverter.class)
    private Instant dateOfBirth;
}
