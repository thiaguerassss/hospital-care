package com.thiago.hospital_care.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
public class UserUpdateDTO {

    @Size(min = 2, max = 100, message = "Nome deve ter no mínimo 3 caracteres e no máximo 100.")
    private String name;

    @Size(min = 5, max = 100, message = "Senha deve ter no mínimo 5 caracteres.")
    private String password;

    @Pattern(
            regexp = "^\\d{4}-(0[1-9]|1[0-2])-(0[1-9]|[12]\\d|3[01])$",
            message = "Data de nascimento inválida. Use o formato YYYY-MM-DD."
    )
    private String birthDate;

    @Pattern(
            regexp = "^(Masculino|Feminino|Outro)$",
            message = "Sexo inválido. Os valores permitidos são: Masculino, Feminino ou Outro."
    )
    private String sex;

    @Pattern(
            regexp = "^(\\(?\\d{2}\\)?\\s?)?(\\d{4,5}\\-?\\d{4})$",
            message = "Número de telefone inválido. Use o formato (11) 99999-9999 ou 11999999999."
    )
    private String phone;

    @Email(message = "E-mail inválido.")
    private String email;

    @Pattern(
            regexp = "^\\d{5}-?\\d{3}$",
            message = "CEP inválido. Use o formato 12345-678."
    )
    private String cep;

    private Integer addressNumber;
}
