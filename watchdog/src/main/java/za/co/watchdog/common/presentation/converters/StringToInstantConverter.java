package za.co.watchdog.common.presentation.converters;

import tools.jackson.databind.JavaType;
import tools.jackson.databind.type.TypeFactory;
import tools.jackson.databind.util.StdConverter;

import java.time.Instant;
import java.time.LocalDate;
import java.time.ZoneOffset;
import java.time.format.DateTimeFormatter;

public class StringToInstantConverter extends StdConverter<String, Instant> {

    @Override
    public Instant convert(String value) {
        if (value == null || value.trim().isEmpty()) {
            return null;
        }

        try {
            return Instant.parse(value);
        } catch (Exception e) {
            try {
                LocalDate date = LocalDate.parse(value, DateTimeFormatter.ISO_LOCAL_DATE);
                return date.atStartOfDay(ZoneOffset.UTC).toInstant();
            } catch (Exception ex) {
                throw new IllegalArgumentException("Could not parse date: " + value, ex);
            }
        }
    }

    @Override
    public JavaType getInputType(TypeFactory typeFactory) {
        return typeFactory.constructType(String.class);
    }

    @Override
    public JavaType getOutputType(TypeFactory typeFactory) {
        return typeFactory.constructType(Instant.class);
    }
}
