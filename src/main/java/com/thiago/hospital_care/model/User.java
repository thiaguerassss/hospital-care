package com.thiago.hospital_care.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.thiago.hospital_care.model.enums.SexEnum;
import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.br.CPF;

import java.time.LocalDate;

@Data
@NoArgsConstructor
@MappedSuperclass
public abstract class User {

    @Column(length = 100, nullable = false)
    @NotBlank(message = "Nome não pode estar vazio.")
    @Size(min = 2, max = 100, message = "Nome deve ter no mínimo 3 caracteres e no máximo 100.")
    private String name;

    @Column(nullable = false, unique = true)
    @NotBlank(message = "CPF não pode estar vazio.")
    @CPF(message = "CPF inválido.")
    private String cpf;

    @Column(length = 100, nullable = false)
    @NotBlank(message = "Senha não pode ser nula/vazia.")
    @Size(min = 5, max = 100, message = "Senha deve ter no mínimo 5 caracteres.")
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    private String password;

    @Column(nullable = false, name = "birth_date")
    @PastOrPresent(message = "Data de nascimento inválida.")
    private LocalDate birthDate;

    @Column(nullable = false)
    private SexEnum sex;

    @Column(nullable = false)
    @Pattern(
            regexp = "^(\\(?\\d{2}\\)?\\s?)?(\\d{4,5}\\-?\\d{4})$",
            message = "Número de telefone inválido. Use o formato (11) 99999-9999 ou 11999999999."
    )
    private String phone;

    @Column(nullable = false, unique = true)
    @Email(message = "E-mail inválido.")
    @NotBlank(message = "E-mail não pode estar vazio.")
    private String email;

    @Column(nullable = false)
    private String address;

    @Column(nullable = false)
    @Pattern(
            regexp = "^\\d{5}-?\\d{3}$",
            message = "CEP inválido. Use o formato 12345-678."
    )
    private String cep;

    @Column(nullable = false)
    private String city;

    @Column(nullable = false)
    private String state;

    public User(String name, String cpf, String password, String birthDate, String sex, String phone, String email,
                String address, String cep, String city, String state){
        this.name = name;
        this.cpf = cpf;
        this.password = password;
        this.birthDate = LocalDate.parse(birthDate);
        this.sex = SexEnum.fromString(sex);
        this.phone = phone;
        this.email = email;
        this.address = address;
        this.cep = cep;
        this.city = city;
        this.state = state;
    }
}
