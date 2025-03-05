package com.thiago.hospital_care.service;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.thiago.hospital_care.model.Appointment;
import com.thiago.hospital_care.repository.AppointmentRepository;
import com.thiago.hospital_care.service.exception.ObjectNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class AppointmentService {

    @Autowired
    private AppointmentRepository appointmentRepository;

    public Appointment findById(Long id){
        return this.appointmentRepository.findById(id)
                .orElseThrow(() -> new ObjectNotFoundException("Consulta não encontrada. ID: " + id));
    }

    @Transactional
    public Appointment create(Appointment appointment){
        return this.appointmentRepository.save(appointment);
    }

    @Transactional
    public Appointment update(Appointment appointment) throws JsonMappingException {
        ObjectMapper objectMapper = new ObjectMapper();
        objectMapper.setSerializationInclusion(JsonInclude.Include.NON_NULL);
        Appointment newAppointment = this.findById(appointment.getId());
        newAppointment = objectMapper.updateValue(newAppointment, appointment);
        return this.appointmentRepository.save(newAppointment);
    }

    public void delete(Long id){
        findById(id);
        this.appointmentRepository.deleteById(id);
    }
}
