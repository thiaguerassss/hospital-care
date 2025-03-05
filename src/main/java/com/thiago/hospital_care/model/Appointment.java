package com.thiago.hospital_care.model;

import com.thiago.hospital_care.model.enums.AppointmentStatusEnum;
import com.thiago.hospital_care.model.enums.AppointmentTypeEnum;
import jakarta.persistence.*;
import jakarta.validation.constraints.Future;
import jakarta.validation.constraints.Size;
import lombok.*;

import java.time.LocalDateTime;

@Entity
@Table(name = "appointment")
@Data
@NoArgsConstructor
public class Appointment {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, name = "date_time")
    @Future(message = "Data inválida.")
    private LocalDateTime dateTime;

    @Column(length = 300)
    @Size(max = 300, message = "São permitidos no máximo 300 caracteres.")
    private String description;

    @Column(nullable = false)
    private AppointmentTypeEnum type;

    @Column(nullable = false)
    private AppointmentStatusEnum status;

    @ManyToOne
    @JoinColumn(name = "patient_id", nullable = false)
    private Patient patient;

    @ManyToOne
    @JoinColumn(name = "doctor_id", nullable = false)
    private Doctor doctor;

    public Appointment(String dateTime, String description, String type, Patient patient){
        this.id = null;
        this.dateTime = LocalDateTime.parse(dateTime);
        this.description = description;
        this.type = AppointmentTypeEnum.fromString(type);
        this.status = AppointmentStatusEnum.SCHEDULED;
        this.patient = patient;
    }
}

