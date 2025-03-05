package com.thiago.hospital_care.controller;

import com.fasterxml.jackson.databind.JsonMappingException;
import com.thiago.hospital_care.dto.DoctorCreateDTO;
import com.thiago.hospital_care.dto.DoctorUpdateDTO;
import com.thiago.hospital_care.model.Doctor;
import com.thiago.hospital_care.service.DoctorService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;

@RestController
@RequestMapping("/doctor")
@Validated
public class DoctorController {

    @Autowired
    private DoctorService doctorService;

    @GetMapping("/{id}")
    public ResponseEntity<Doctor> findById(@PathVariable("id") Long id){
        Doctor doctor = this.doctorService.findById(id);
        return ResponseEntity.ok(doctor);
    }

    @PostMapping
    public ResponseEntity<Doctor> create(@Valid @RequestBody DoctorCreateDTO data){
        Doctor doctor = this.doctorService.create(data);
        URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(doctor.getId()).toUri();
        return ResponseEntity.created(uri).body(doctor);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Doctor> update(@Valid @RequestBody DoctorUpdateDTO data, @PathVariable("id") Long id)
            throws JsonMappingException {
        data.setId(id);
        Doctor doctor = this.doctorService.update(data);
        return ResponseEntity.ok(doctor);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable("id") Long id){
        this.doctorService.delete(id);
        return ResponseEntity.noContent().build();
    }
}
