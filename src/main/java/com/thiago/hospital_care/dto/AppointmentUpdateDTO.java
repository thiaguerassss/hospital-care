package com.thiago.hospital_care.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
public class AppointmentUpdateDTO {

    private Long id;

    @NotBlank(message = "Data e hora não podem ser nulos/vazios.")
    private String dateTime;

    @Size(max = 300, message = "São permitidos no máximo 300 caracteres.")
    private String description;

    @NotBlank(message = "Status não pode ser nulo/vazio.")
    private String status;
}
