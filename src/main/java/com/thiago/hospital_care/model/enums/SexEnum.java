package com.thiago.hospital_care.model.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public enum SexEnum {
    MALE ("Masculino"),
    FEMALE("Feminino"),
    OTHER("Outro");

    private final String description;
}
