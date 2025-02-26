package com.thiago.hospital_care.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.thiago.hospital_care.model.enums.SpecialtyEnum;
import jakarta.persistence.*;
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
    private String crm;

    @Column(nullable = false)
    private SpecialtyEnum specialty;

    @OneToMany(mappedBy = "doctor", cascade = CascadeType.ALL)
    @JsonIgnore
    private List<Appointment> appointments = new ArrayList<>();

    public Doctor(String name, String cpf, String password, String birthDate, String sex, String phone, String email,
                  String address, String cep, String city, String state, String crm, String specialty){
        super(name, cpf, password, birthDate, sex, phone, email, address, cep, city, state);
        this.id = null;
        this.crm = crm;
        this.specialty = SpecialtyEnum.fromString(specialty);
    }
}
