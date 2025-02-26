package com.thiago.hospital_care.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "patient")
@Data
@NoArgsConstructor
@EqualsAndHashCode(callSuper = true)
public class Patient extends User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToMany(mappedBy = "patient", cascade = CascadeType.ALL)
    @JsonIgnore
    private List<Appointment> appointments = new ArrayList<>();

    public Patient(String name, String cpf, String password, String birthDate, String sex, String phone, String email,
                   String address, String cep, String city, String state){
        super(name, cpf, password, birthDate, sex, phone, email, address, cep, city, state);
        this.id = null;
    }
}
