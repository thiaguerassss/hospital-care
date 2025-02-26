package com.thiago.hospital_care.model.converter;

import com.thiago.hospital_care.model.enums.AppointmentTypeEnum;
import jakarta.persistence.AttributeConverter;
import jakarta.persistence.Converter;

import java.util.Objects;
import java.util.stream.Stream;

@Converter(autoApply = true)
public class AppointmentTypeConverter implements AttributeConverter<AppointmentTypeEnum, String> {
    @Override
    public String convertToDatabaseColumn(AppointmentTypeEnum appointmentTypeEnum) {
        if(Objects.isNull(appointmentTypeEnum)) return null;
        return appointmentTypeEnum.getDescription();
    }

    @Override
    public AppointmentTypeEnum convertToEntityAttribute(String s) {
        if(Objects.isNull(s)) return null;
        return Stream.of(AppointmentTypeEnum.values())
                .filter(a -> a.getDescription().equals(s))
                .findFirst()
                .orElseThrow(IllegalArgumentException::new);
    }
}
