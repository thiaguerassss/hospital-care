package com.thiago.hospital_care.model.enums;

import lombok.Getter;

@Getter
public enum SexEnum {
    MALE ("Masculino"),
    FEMALE("Feminino"),
    OTHER("Outro");

    private final String description;

    SexEnum(String description){
        this.description = description;
    }

    public static SexEnum fromString(String value) {
        for (SexEnum specialty : SexEnum.values()) {
            if (specialty.getDescription().equalsIgnoreCase(value)) {
                return specialty;
            }
        }
        throw new IllegalArgumentException("Sexo inválido: " + value);
    }
}
