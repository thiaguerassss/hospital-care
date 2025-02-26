package com.thiago.hospital_care.model.converter;

import com.thiago.hospital_care.model.enums.AppointmentStatusEnum;
import jakarta.persistence.AttributeConverter;
import jakarta.persistence.Converter;

import java.util.Objects;
import java.util.stream.Stream;

@Converter(autoApply = true)
public class AppointmentStatusConverter implements AttributeConverter<AppointmentStatusEnum, String> {
    @Override
    public String convertToDatabaseColumn(AppointmentStatusEnum appointmentStatusEnum) {
        if(Objects.isNull(appointmentStatusEnum)) return null;
        return appointmentStatusEnum.getDescription();
    }

    @Override
    public AppointmentStatusEnum convertToEntityAttribute(String s) {
        if(Objects.isNull(s)) return null;
        return Stream.of(AppointmentStatusEnum.values())
                .filter(a -> a.getDescription().equals(s))
                .findFirst()
                .orElseThrow(IllegalArgumentException::new);
    }
}
