package com.thiago.hospital_care.service;

import com.fasterxml.jackson.databind.JsonMappingException;
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
import java.util.Objects;

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
        Patient patient = this.findById(data.getId());
        fromDTO(data, patient);
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

    private void fromDTO(PatientUpdateDTO data, Patient patient){
        if (Objects.nonNull(data.getName())) patient.setName(data.getName());
        if (Objects.nonNull(data.getPassword())) patient.setPassword(data.getPassword());
        if (Objects.nonNull(data.getBirthDate())) patient.setBirthDate(LocalDate.parse(data.getBirthDate()));
        if (Objects.nonNull(data.getSex())) patient.setSex(SexEnum.fromString(data.getSex()));
        if (Objects.nonNull(data.getPhone())) patient.setPhone(data.getPhone());
        if (Objects.nonNull(data.getEmail())) patient.setEmail(data.getEmail());
        if (Objects.nonNull(data.getCep())) patient.setCep(data.getCep());
        if (Objects.nonNull(data.getAddressNumber())) patient.setAddressNumber(data.getAddressNumber());
    }
}
