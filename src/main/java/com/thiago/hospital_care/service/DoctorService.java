package com.thiago.hospital_care.service;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.thiago.hospital_care.model.Doctor;
import com.thiago.hospital_care.repository.DoctorRepository;
import com.thiago.hospital_care.service.exception.ObjectNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class DoctorService {

    @Autowired
    private DoctorRepository doctorRepository;

    public Doctor findById(Long id){
        return this.doctorRepository.findById(id)
                .orElseThrow(() -> new ObjectNotFoundException("Médico não encontrado. ID: " + id));
    }

    @Transactional
    public Doctor create(Doctor doctor){
        return this.doctorRepository.save(doctor);
    }

    @Transactional
    public Doctor update(Doctor doctor) throws JsonMappingException {
        ObjectMapper objectMapper = new ObjectMapper();
        objectMapper.setSerializationInclusion(JsonInclude.Include.NON_NULL);
        Doctor newDoctor = this.findById(doctor.getId());
        objectMapper.updateValue(newDoctor, doctor);
        return this.doctorRepository.save(newDoctor);
    }

    public void delete(Long id){
        findById(id);
        this.doctorRepository.deleteById(id);
    }
}
