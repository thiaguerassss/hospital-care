package com.thiago.hospital_care.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.Data;


@Data
public class AppointmentCreateDTO {

    @NotBlank(message = "Data e hora não podem ser nulos/vazios.")
    @Pattern(
            regexp = "^\\d{4}-(0[1-9]|1[0-2])-(0[1-9]|[12]\\d|3[01])T([01]\\d|2[0-3]):([0-5]\\d):([0-5]\\d)$",
            message = "Data e hora inválidas. Use o formato yyyy-MM-dd'T'HH:mm:ss"
    )
    private String dateTime;

    @Size(max = 300, message = "São permitidos no máximo 300 caracteres.")
    private String description;

    @NotBlank(message = "Tipo da consulta não pode ser nulo/vazio.")
    @Pattern(
            regexp = "^(Agendada|Cancelada|Concluída)$",
            message = "Status inválido. Os valores permitidos são: Agendada, Cancelada ou Concluída."
    )
    private String type;

    private Long patientId;
}
