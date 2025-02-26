package com.thiago.hospital_care.model.enums;

import lombok.Getter;

@Getter
public enum AppointmentStatusEnum {
    SCHEDULED("Agendada"),
    CANCELLED("Cancelada"),
    COMPLETED("Concluída");

    private final String description;

    AppointmentStatusEnum(String description){
        this.description = description;
    }
}
