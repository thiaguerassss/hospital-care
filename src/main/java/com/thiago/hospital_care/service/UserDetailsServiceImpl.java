package com.thiago.hospital_care.service;

import com.thiago.hospital_care.model.Doctor;
import com.thiago.hospital_care.model.Patient;
import com.thiago.hospital_care.repository.DoctorRepository;
import com.thiago.hospital_care.repository.PatientRepository;
import com.thiago.hospital_care.security.UserSpringSecurity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {

    @Autowired
    private DoctorRepository doctorRepository;

    @Autowired
    private PatientRepository patientRepository;

    @Override
    public UserDetails loadUserByUsername(String cpf) throws UsernameNotFoundException {
        Doctor doctor = doctorRepository.findByCpf(cpf);
        if (doctor != null) {
            return new UserSpringSecurity(
                    doctor.getId(),
                    doctor.getCpf(),
                    doctor.getPassword(),
                    doctor.getProfiles()
            );
        }

        Patient patient = patientRepository.findByCpf(cpf);
        if (patient != null) {
            return new UserSpringSecurity(
                    patient.getId(),
                    patient.getCpf(),
                    patient.getPassword(),
                    patient.getProfiles()
            );
        }

        throw new UsernameNotFoundException("Usuário não encontrado com o CPF: " + cpf);
    }
}
