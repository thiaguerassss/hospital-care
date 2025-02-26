package com.thiago.hospital_care.model.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public enum SpecialtyEnum {
    CARDIOLOGY("Cardiologia"),
    ORTHOPEDICS("Ortopedia"),
    PEDIATRICS("Pediatria"),
    DERMATOLOGY("Dermatologia"),
    GYNECOLOGY("Ginecologia"),
    UROLOGY("Urologia"),
    GENERAL_CLINICAL("Clínico geral");

    private final String description;

}
