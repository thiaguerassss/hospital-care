package com.thiago.hospital_care.controller;

import com.fasterxml.jackson.databind.JsonMappingException;
import com.thiago.hospital_care.dto.AppointmentCreateDTO;
import com.thiago.hospital_care.dto.AppointmentUpdateDTO;
import com.thiago.hospital_care.model.Appointment;
import com.thiago.hospital_care.service.AppointmentService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;

@RestController
@RequestMapping("/appointment")
@Validated
public class AppointmentController {

    @Autowired
    private AppointmentService appointmentService;

    @GetMapping("/{id}")
    public ResponseEntity<Appointment> findById(@PathVariable("id") Long id){
        Appointment appointment = this.appointmentService.findById(id);
        return ResponseEntity.ok(appointment);
    }

    @PostMapping
    public ResponseEntity<Appointment> create(@Valid @RequestBody AppointmentCreateDTO data){
        Appointment appointment = this.appointmentService.create(data);
        URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(appointment.getId()).toUri();
        return ResponseEntity.created(uri).body(appointment);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Appointment> update(@Valid @RequestBody AppointmentUpdateDTO data, @PathVariable("id") Long id)
            throws JsonMappingException {
        data.setId(id);
        Appointment appointment = this.appointmentService.update(data);
        return ResponseEntity.ok(appointment);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable("id") Long id){
        this.appointmentService.delete(id);
        return ResponseEntity.noContent().build();
    }
}
