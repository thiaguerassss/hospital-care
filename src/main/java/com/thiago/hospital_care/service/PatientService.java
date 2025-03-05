package com.thiago.hospital_care.service;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.thiago.hospital_care.dto.PatientCreateDTO;
import com.thiago.hospital_care.dto.PatientUpdateDTO;
import com.thiago.hospital_care.model.Patient;
import com.thiago.hospital_care.model.enums.SexEnum;
import com.thiago.hospital_care.repository.PatientRepository;
import com.thiago.hospital_care.service.exception.ObjectNotFoundException;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;

@Service
public class PatientService {

    @Autowired
    private PatientRepository patientRepository;

    public Patient findById(Long id){
        return this.patientRepository.findById(id)
                .orElseThrow(() -> new ObjectNotFoundException("Paciente não encontrado. ID: " + id));
    }

    @Transactional
    public Patient create(@Valid PatientCreateDTO data){
        Patient patient = fromDTO(data);
        return this.patientRepository.save(patient);
    }

    @Transactional
    public Patient update(@Valid PatientUpdateDTO data) throws JsonMappingException {
        ObjectMapper objectMapper = new ObjectMapper();
        objectMapper.setSerializationInclusion(JsonInclude.Include.NON_NULL);
        Patient patientData = fromDTO(data);
        Patient patient = this.findById(patientData.getId());
        patient = objectMapper.updateValue(patient, patientData);
        return this.patientRepository.save(patient);
    }

    public void delete(Long id){
        findById(id);
        this.patientRepository.deleteById(id);
    }

    private Patient fromDTO(PatientCreateDTO data){
        String name = data.getName();
        String cpf = data.getCpf();
        String password = data.getPassword();
        String birthDate = data.getBirthDate();
        String sex = data.getSex();
        String phone = data.getPhone();
        String email = data.getEmail();
        String cep = data.getCep();
        Integer addressNumber = data.getAddressNumber();
        return new Patient(name, cpf, password, birthDate, sex, phone, email, cep, addressNumber);
    }

    private Patient fromDTO(PatientUpdateDTO data){
        Patient patient = new Patient();
        patient.setId(data.getId());
        patient.setName(data.getName());
        patient.setPassword(data.getPassword());
        patient.setBirthDate(LocalDate.parse(data.getBirthDate()));
        patient.setSex(SexEnum.fromString(data.getSex()));
        patient.setPhone(data.getPhone());
        patient.setEmail(data.getEmail());
        patient.setCep(data.getCep());
        patient.setAddressNumber(data.getAddressNumber());
        return patient;
    }
}
