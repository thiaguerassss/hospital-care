package com.thiago.hospital_care.model.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public enum AppointmentStatusEnum {
    SCHEDULED("Agendada"),
    CANCELLED("Cancelada"),
    COMPLETED("Concluída");

    private final String description;
}
