package com.thiago.hospital_care.model.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public enum AppointmentTypeEnum {
    ROUTINE("Rotina"),
    EMERGENCY("Emergência"),
    EXAM("Exame");

    private final String description;
}
