package com.thiago.hospital_care.dto;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = true)
public class PatientUpdateDTO extends UserUpdateDTO {

    private Long id;
}
