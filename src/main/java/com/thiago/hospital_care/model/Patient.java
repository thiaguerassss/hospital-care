package com.thiago.hospital_care.model;

import com.thiago.hospital_care.model.enums.SexEnum;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.PastOrPresent;
import jakarta.validation.constraints.Size;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.br.CPF;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "patient")
@Data
@NoArgsConstructor
public class Patient {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(length = 100, nullable = false)
    @NotBlank(message = "Nome não pode estar vazio.")
    @Size(min = 2, max = 100, message = "Nome deve ter no mínimo 3 caracteres e no máximo 100.")
    private String name;

    @Column(nullable = false, unique = true)
    @NotBlank(message = "CPF não pode estar vazio.")
    @CPF(message = "CPF inválido.")
    private String cpf;

    @Column(nullable = false, name = "birth_date")
    @PastOrPresent(message = "Data de nascimento inválida.")
    private LocalDate birthDate;

    @Column(nullable = false)
    private SexEnum sex;

    @Column(nullable = false)
    private String phone; // ADICIONAR VALIDAÇÃO

    @Column(nullable = false, unique = true)
    private String email; // ADICIONAR VALIDAÇÃO

    @Column(nullable = false)
    private String address; // ADICIONAR VALIDAÇÃO

    @Column(nullable = false)
    private String cep; // ADICIONAR VALIDAÇÃO

    @Column(nullable = false)
    private String city; // ADICIONAR VALIDAÇÃO

    @Column(nullable = false)
    private String state; // ADICIONAR VALIDAÇÃO

    @OneToMany(mappedBy = "patient", cascade = CascadeType.ALL)
    private List<Appointment> consultations = new ArrayList<>();

    public Patient(String name, String cpf, String birthDate, String sex, String phone, String email, String address,
                   String cep, String city, String state){
        this.id = null;
        this.name = name;
        this.cpf = cpf;
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
