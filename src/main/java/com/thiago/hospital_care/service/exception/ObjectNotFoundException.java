package com.thiago.hospital_care.service.exception;


import jakarta.persistence.EntityNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.NOT_FOUND)
public class ObjectNotFoundException extends EntityNotFoundException {

    public ObjectNotFoundException(String msg) { super(msg); }
}
