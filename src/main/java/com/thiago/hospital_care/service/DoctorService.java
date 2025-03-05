package com.thiago.hospital_care.service;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.thiago.hospital_care.dto.DoctorCreateDTO;
import com.thiago.hospital_care.dto.DoctorUpdateDTO;
import com.thiago.hospital_care.model.Doctor;
import com.thiago.hospital_care.model.enums.SexEnum;
import com.thiago.hospital_care.model.enums.SpecialtyEnum;
import com.thiago.hospital_care.repository.DoctorRepository;
import com.thiago.hospital_care.service.exception.ObjectNotFoundException;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;

@Service
public class DoctorService {

    @Autowired
    private DoctorRepository doctorRepository;

    public Doctor findById(Long id){
        return this.doctorRepository.findById(id)
                .orElseThrow(() -> new ObjectNotFoundException("Médico não encontrado. ID: " + id));
    }

    @Transactional
    public Doctor create(@Valid DoctorCreateDTO data){
        Doctor doctor = fromDTO(data);
        return this.doctorRepository.save(doctor);
    }

    @Transactional
    public Doctor update(@Valid DoctorUpdateDTO data) throws JsonMappingException {
        ObjectMapper objectMapper = new ObjectMapper();
        objectMapper.setSerializationInclusion(JsonInclude.Include.NON_NULL);
        Doctor doctorData = fromDTO(data);
        Doctor doctor = this.findById(doctorData.getId());
        doctor = objectMapper.updateValue(doctor, doctorData);
        return this.doctorRepository.save(doctor);
    }

    public void delete(Long id){
        findById(id);
        this.doctorRepository.deleteById(id);
    }

    private Doctor fromDTO(DoctorCreateDTO data){
        String name = data.getName();
        String cpf = data.getCpf();
        String password = data.getPassword();
        String birthDate = data.getBirthDate();
        String sex = data.getSex();
        String phone = data.getPhone();
        String email = data.getEmail();
        String cep = data.getCep();
        Integer addressNumber = data.getAddressNumber();
        String crm = data.getCrm();
        String specialty = data.getSpecialty();
        return new Doctor(name, cpf, password, birthDate, sex, phone, email, cep, addressNumber, crm, specialty);
    }

    private Doctor fromDTO(DoctorUpdateDTO data){
        Doctor doctor = new Doctor();
        doctor.setId(data.getId());
        doctor.setName(data.getName());
        doctor.setPassword(data.getPassword());
        doctor.setBirthDate(LocalDate.parse(data.getBirthDate()));
        doctor.setSex(SexEnum.fromString(data.getSex()));
        doctor.setPhone(data.getPhone());
        doctor.setEmail(data.getEmail());
        doctor.setCep(data.getCep());
        doctor.setAddressNumber(data.getAddressNumber());
        doctor.setCrm(data.getCrm());
        doctor.setSpecialty(SpecialtyEnum.fromString(data.getSpecialty()));
        return doctor;
    }
}
