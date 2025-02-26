package com.thiago.hospital_care.model.enums;

import lombok.Getter;

@Getter
public enum AppointmentTypeEnum {
    ROUTINE("Rotina"),
    EMERGENCY("Emergência"),
    EXAM("Exame");

    private final String description;

    AppointmentTypeEnum(String description){
        this.description = description;
    }

    public static AppointmentTypeEnum fromString(String value) {
        for (AppointmentTypeEnum specialty : AppointmentTypeEnum.values()) {
            if (specialty.getDescription().equalsIgnoreCase(value)) {
                return specialty;
            }
        }
        throw new IllegalArgumentException("Tipo inválido: " + value);
    }
}
