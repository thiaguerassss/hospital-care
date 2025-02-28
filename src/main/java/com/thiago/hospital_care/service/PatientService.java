package com.thiago.hospital_care.service;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.thiago.hospital_care.model.Patient;
import com.thiago.hospital_care.repository.PatientRepository;
import com.thiago.hospital_care.service.exception.ObjectNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class PatientService {

    @Autowired
    private PatientRepository patientRepository;

    public Patient findById(Long id){
        return this.patientRepository.findById(id)
                .orElseThrow(() -> new ObjectNotFoundException("Paciente não encontrado. ID: " + id));
    }

    @Transactional
    public Patient create(Patient patient){
        return this.patientRepository.save(patient);
    }

    @Transactional
    public Patient update(Patient patient) throws JsonMappingException {
        ObjectMapper objectMapper = new ObjectMapper();
        objectMapper.setSerializationInclusion(JsonInclude.Include.NON_NULL);
        Patient newPatient = this.findById(patient.getId());
        objectMapper.updateValue(newPatient, patient);
        return this.patientRepository.save(newPatient);
    }

    public void delete(Long id){
        findById(id);
        this.patientRepository.deleteById(id);
    }
}
