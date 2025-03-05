package com.thiago.hospital_care.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = true)
public class DoctorUpdateDTO extends UserUpdateDTO{

    private Long id;

    @NotBlank(message = "CRM não pode ser nulo/vazio.")
    private String crm;

    @NotBlank(message = "Especialidade não pode estar vazia.")
    @Pattern(
            regexp = "^(Cardiologia|Ortopedia|Pediatria|Dermatologia|Ginecologia|Urologia|Clínico geral)$",
            message = "Especialidade inválida."
    )
    private String specialty;
}
