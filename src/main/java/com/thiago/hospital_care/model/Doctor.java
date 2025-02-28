package com.thiago.hospital_care.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.thiago.hospital_care.model.enums.SpecialtyEnum;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import lombok.*;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "doctor")
@Data
@NoArgsConstructor
@EqualsAndHashCode(callSuper = true)
public class Doctor extends User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(unique = true, nullable = false)
    @NotBlank(message = "CRM não pode ser nulo/vazio.")
    private String crm;

    @Column(nullable = false)
    private SpecialtyEnum specialty;

    @OneToMany(mappedBy = "doctor", cascade = CascadeType.ALL, orphanRemoval = true)
    @JsonIgnore
    private List<Appointment> appointments = new ArrayList<>();

    public Doctor(String name, String cpf, String password, String birthDate, String sex, String phone, String email,
                  String cep, Integer addressNumber, String crm, String specialty){
        super(name, cpf, password, birthDate, sex, phone, email, cep, addressNumber);
        this.id = null;
        this.crm = crm;
        this.specialty = SpecialtyEnum.fromString(specialty);
    }
}
