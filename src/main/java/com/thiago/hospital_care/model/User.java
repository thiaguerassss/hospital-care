package com.thiago.hospital_care.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.thiago.hospital_care.model.enums.ProfileEnum;
import com.thiago.hospital_care.model.enums.SexEnum;
import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.br.CPF;

import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;

@Data
@NoArgsConstructor
@MappedSuperclass
public abstract class User {

    @Column(length = 100, nullable = false)
    @NotBlank(message = "Nome não pode estar vazio.")
    @Size(min = 2, max = 100, message = "Nome deve ter no mínimo 3 caracteres e no máximo 100.")
    private String name;

    @Column(nullable = false, unique = true, updatable = false)
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
    @NotBlank(message = "Telefone não pode ser nulo/vazio.")
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
    @NotBlank(message = "CEP não pode ser nulo/vazio.")
    @Pattern(
            regexp = "^\\d{5}-?\\d{3}$",
            message = "CEP inválido. Use o formato 12345-678."
    )
    private String cep;

    @Column(nullable = false)
    private Integer addressNumber;

    @Column(nullable = false)
    @NotBlank(message = "Endereço não pode ser nulo/vazio.")
    private String address;

    @Column(nullable = false)
    @NotBlank(message = "Cidade não pode ser nula/vazia.")
    private String city;

    @Column(nullable = false)
    @NotBlank(message = "Estado não pode ser nulo/vazio.")
    private String state;

    @ElementCollection(fetch = FetchType.EAGER)
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    @CollectionTable(name = "user_profile")
    @Column(name = "profile", nullable = false)
    private Set<Integer> profiles = new HashSet<>();

    public Set<ProfileEnum> getProfiles(){
        return profiles.stream().map(ProfileEnum::toEnum).collect(Collectors.toSet());
    }

    public void addProfile(ProfileEnum profileEnum){
        profiles.add(profileEnum.getCode());
    }

    public User(String name, String cpf, String password, String birthDate, String sex, String phone, String email,
                String cep, Integer addressNumber){
        this.name = name;
        this.cpf = cpf;
        this.password = password;
        this.birthDate = LocalDate.parse(birthDate);
        this.sex = SexEnum.fromString(sex);
        this.phone = phone;
        this.email = email;
        this.cep = cep;
        this.addressNumber = addressNumber;
    }
}
