package com.thiago.hospital_care.service;

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
    public Patient update(Patient patient){
        Patient newPatient = this.findById(patient.getId());
        newPatient.setName(patient.getName());
        newPatient.setPassword(patient.getPassword());
        newPatient.setSex(patient.getSex());
        newPatient.setPhone(patient.getPhone());
        newPatient.setEmail(patient.getEmail());
        newPatient.setAddress(patient.getAddress());
        newPatient.setCep(patient.getCep());
        newPatient.setCity(patient.getCity());
        newPatient.setState(patient.getState());
        // ADICIONAR OBJECTMAPPER
        return this.patientRepository.save(newPatient);
    }
}
