package com.thiago.hospital_care.dto;

import jakarta.validation.constraints.Pattern;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = true)
public class DoctorUpdateDTO extends UserUpdateDTO{

    private Long id;

    private String crm;

    @Pattern(
            regexp = "^(Cardiologia|Ortopedia|Pediatria|Dermatologia|Ginecologia|Urologia|Clínico geral)$",
            message = "Especialidade inválida."
    )
    private String specialty;
}
