package com.thiago.hospital_care.service;

import com.fasterxml.jackson.databind.JsonMappingException;
import com.thiago.hospital_care.dto.DoctorCreateDTO;
import com.thiago.hospital_care.dto.DoctorUpdateDTO;
import com.thiago.hospital_care.model.Doctor;
import com.thiago.hospital_care.model.enums.ProfileEnum;
import com.thiago.hospital_care.model.enums.SexEnum;
import com.thiago.hospital_care.model.enums.SpecialtyEnum;
import com.thiago.hospital_care.repository.DoctorRepository;
import com.thiago.hospital_care.service.exception.ObjectNotFoundException;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.Objects;

@Service
public class DoctorService {

    @Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;

    @Autowired
    private DoctorRepository doctorRepository;

    public Doctor findById(Long id){
        return this.doctorRepository.findById(id)
                .orElseThrow(() -> new ObjectNotFoundException("Médico não encontrado. ID: " + id));
    }

    @Transactional
    public Doctor create(@Valid DoctorCreateDTO data){
        Doctor doctor = fromDTO(data);
        doctor.setPassword(bCryptPasswordEncoder.encode(doctor.getPassword()));
        doctor.addProfile(ProfileEnum.ADMIN);
        return this.doctorRepository.save(doctor);
    }

    @Transactional
    public Doctor update(@Valid DoctorUpdateDTO data) throws JsonMappingException {
        Doctor doctor = this.findById(data.getId());
        fromDTO(data, doctor);
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

    private void fromDTO(DoctorUpdateDTO data, Doctor doctor){
        if (Objects.nonNull((data.getName()))) doctor.setName(data.getName());
        if (Objects.nonNull((data.getPassword()))){
            doctor.setPassword(data.getPassword());
            doctor.setPassword(bCryptPasswordEncoder.encode(doctor.getPassword()));
        }
        if (Objects.nonNull((data.getBirthDate()))) doctor.setBirthDate(LocalDate.parse(data.getBirthDate()));
        if (Objects.nonNull((data.getSex()))) doctor.setSex(SexEnum.fromString(data.getSex()));
        if (Objects.nonNull((data.getPhone()))) doctor.setPhone(data.getPhone());
        if (Objects.nonNull((data.getEmail()))) doctor.setEmail(data.getEmail());
        if (Objects.nonNull((data.getCep()))) doctor.setCep(data.getCep());
        if (Objects.nonNull((data.getAddressNumber()))) doctor.setAddressNumber(data.getAddressNumber());
        if (Objects.nonNull((data.getCrm()))) doctor.setCrm(data.getCrm());
        if (Objects.nonNull((data.getSpecialty()))) doctor.setSpecialty(SpecialtyEnum.fromString(data.getSpecialty()));
    }
}
