package com.thiago.hospital_care.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.Data;
import org.hibernate.validator.constraints.br.CPF;

@Data
public class UserCreateDTO {

    @NotBlank(message = "Nome não pode estar vazio.")
    @Size(min = 2, max = 100, message = "Nome deve ter no mínimo 3 caracteres e no máximo 100.")
    private String name;

    @NotBlank(message = "CPF não pode estar vazio.")
    @CPF(message = "CPF inválido.")
    private String cpf;

    @NotBlank(message = "Senha não pode ser nula/vazia.")
    @Size(min = 5, max = 100, message = "Senha deve ter no mínimo 5 caracteres.")
    private String password;

    @NotBlank(message = "Data de nascimento não pode estar vazia.")
    @Pattern(
            regexp = "^\\d{4}-\\d{2}-\\d{2}$",
            message = "Data de nascimento inválida. Use o formato YYYY-MM-DD."
    )
    private String birthDate;

    @NotBlank(message = "Sexo não pode estar vazio.")
    @Pattern(
            regexp = "^(Masculino|Feminino|Outro)$",
            message = "Sexo inválido. Os valores permitidos são: Masculino, Feminino ou Outro."
    )
    private String sex;

    @NotBlank(message = "Telefone não pode ser nulo/vazio.")
    @Pattern(
            regexp = "^(\\(?\\d{2}\\)?\\s?)?(\\d{4,5}\\-?\\d{4})$",
            message = "Número de telefone inválido. Use o formato (11) 99999-9999 ou 11999999999."
    )
    private String phone;

    @Email(message = "E-mail inválido.")
    @NotBlank(message = "E-mail não pode estar vazio.")
    private String email;

    @NotBlank(message = "CEP não pode ser nulo/vazio.")
    @Pattern(
            regexp = "^\\d{5}-?\\d{3}$",
            message = "CEP inválido. Use o formato 12345-678."
    )
    private String cep;

    private Integer addressNumber;
}
