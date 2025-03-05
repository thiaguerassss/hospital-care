package com.thiago.hospital_care.controller;

import com.fasterxml.jackson.databind.JsonMappingException;
import com.thiago.hospital_care.dto.PatientCreateDTO;
import com.thiago.hospital_care.dto.PatientUpdateDTO;
import com.thiago.hospital_care.model.Patient;
import com.thiago.hospital_care.service.PatientService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;

@RestController
@RequestMapping("/patient")
@Validated
public class PatientController {

    @Autowired
    private PatientService patientService;

    @GetMapping("/{id}")
    public ResponseEntity<Patient> findById(@PathVariable("id") Long id){
        Patient patient = this.patientService.findById(id);
        return ResponseEntity.ok(patient);
    }

    @PostMapping
    public ResponseEntity<Patient> create(@Valid @RequestBody PatientCreateDTO data){
        Patient patient = this.patientService.create(data);
        URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(patient.getId()).toUri();
        return ResponseEntity.created(uri).body(patient);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Patient> update(@Valid @RequestBody PatientUpdateDTO data, @PathVariable("id") Long id)
            throws JsonMappingException {
        data.setId(id);
        Patient patient = this.patientService.update(data);
        return ResponseEntity.ok(patient);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable("id") Long id){
        this.patientService.delete(id);
        return ResponseEntity.noContent().build();
    }
}
