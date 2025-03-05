package com.thiago.hospital_care.service;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.thiago.hospital_care.dto.AppointmentCreateDTO;
import com.thiago.hospital_care.dto.AppointmentUpdateDTO;
import com.thiago.hospital_care.model.Appointment;
import com.thiago.hospital_care.model.Patient;
import com.thiago.hospital_care.model.enums.AppointmentStatusEnum;
import com.thiago.hospital_care.repository.AppointmentRepository;
import com.thiago.hospital_care.service.exception.ObjectNotFoundException;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;

@Service
public class AppointmentService {

    @Autowired
    private AppointmentRepository appointmentRepository;

    @Autowired
    private PatientService patientService;

    public Appointment findById(Long id){
        return this.appointmentRepository.findById(id)
                .orElseThrow(() -> new ObjectNotFoundException("Consulta não encontrada. ID: " + id));
    }

    @Transactional
    public Appointment create(@Valid AppointmentCreateDTO data){
        Appointment appointment = fromDTO(data);
        return this.appointmentRepository.save(appointment);
    }

    @Transactional
    public Appointment update(@Valid AppointmentUpdateDTO data) throws JsonMappingException {
        ObjectMapper objectMapper = new ObjectMapper();
        objectMapper.setSerializationInclusion(JsonInclude.Include.NON_NULL);
        Appointment appointmentData = fromDTO(data);
        Appointment appointment = this.findById(appointmentData.getId());
        appointment = objectMapper.updateValue(appointment, appointmentData);
        return this.appointmentRepository.save(appointment);
    }

    public void delete(Long id){
        findById(id);
        this.appointmentRepository.deleteById(id);
    }

    private Appointment fromDTO(AppointmentCreateDTO data){
        String dateTime = data.getDateTime();
        String description = data.getDescription();
        String type = data.getType();
        Patient patient = this.patientService.findById(data.getPatientId());
        return new Appointment(dateTime, description, type, patient);
    }

    private Appointment fromDTO(AppointmentUpdateDTO data){
        Appointment appointment = new Appointment();
        appointment.setId(data.getId());
        appointment.setDateTime(LocalDateTime.parse(data.getDateTime()));
        appointment.setDescription(data.getDescription());
        appointment.setStatus(AppointmentStatusEnum.fromString(data.getStatus()));
        return appointment;
    }
}
