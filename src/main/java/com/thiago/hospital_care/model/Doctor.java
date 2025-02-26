package com.thiago.hospital_care.model;

import com.thiago.hospital_care.model.enums.SpecialtyEnum;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.PastOrPresent;
import jakarta.validation.constraints.Size;
import lombok.*;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "doctor")
@Data
@NoArgsConstructor
public class Doctor {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(length = 100, nullable = false)
    @NotBlank(message = "Nome não pode estar vazio.")
    @Size(min = 2, max = 100, message = "Nome deve ter no mínimo 3 caracteres e no máximo 100.")
    private String name;

    @Column(nullable = false, name = "birth_date")
    @PastOrPresent(message = "Data de nascimento inválida.")
    private LocalDate birthDate;

    @Column(unique = true, nullable = false)
    private String crm;

    @Column(nullable = false)
    private SpecialtyEnum specialty;

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

    @OneToMany(mappedBy = "doctor", cascade = CascadeType.ALL)
    private List<Appointment> appointments = new ArrayList<>();

    public Doctor(String name, String birthDate, String crm, String specialty, String email, String address, String cep, String city,
                  String state){
        this.id = null;
        this.name = name;
        this.birthDate = LocalDate.parse(birthDate);
        this.crm = crm;
        this.specialty = SpecialtyEnum.fromString(specialty);
        this.email = email;
        this.address = address;
        this.cep = cep;
        this.city = city;
        this.state = state;
    }
}
