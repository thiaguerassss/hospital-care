package com.thiago.hospital_care.model.converter;

import com.thiago.hospital_care.model.enums.SexEnum;
import jakarta.persistence.AttributeConverter;
import jakarta.persistence.Converter;

import java.util.Objects;
import java.util.stream.Stream;

@Converter(autoApply = true)
public class SexEnumConverter implements AttributeConverter<SexEnum, String> {
    @Override
    public String convertToDatabaseColumn(SexEnum sex) {
        if(Objects.isNull(sex)) return null;
        return sex.getDescription();
    }

    @Override
    public SexEnum convertToEntityAttribute(String sex) {
        if(Objects.isNull(sex)) return null;
        return Stream.of(SexEnum.values())
                .filter(s -> s.getDescription().equals(sex))
                .findFirst()
                .orElseThrow(IllegalArgumentException::new);
    }
}
