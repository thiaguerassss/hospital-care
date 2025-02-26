package com.thiago.hospital_care.model.converter;

import com.thiago.hospital_care.model.enums.AppointmentTypeEnum;
import com.thiago.hospital_care.model.enums.SpecialtyEnum;
import jakarta.persistence.AttributeConverter;
import jakarta.persistence.Converter;

import java.util.Objects;
import java.util.stream.Stream;

@Converter(autoApply = true)
public class SpecialtyConverter implements AttributeConverter<SpecialtyEnum, String> {

    @Override
    public String convertToDatabaseColumn(SpecialtyEnum specialtyEnum) {
        if(Objects.isNull(specialtyEnum)) return null;
        return specialtyEnum.getDescription();
    }

    @Override
    public SpecialtyEnum convertToEntityAttribute(String s) {
        if(Objects.isNull(s)) return null;
        return Stream.of(SpecialtyEnum.values())
                .filter(a -> a.getDescription().equals(s))
                .findFirst()
                .orElseThrow(IllegalArgumentException::new);
    }
}
