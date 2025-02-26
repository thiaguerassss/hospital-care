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
}
