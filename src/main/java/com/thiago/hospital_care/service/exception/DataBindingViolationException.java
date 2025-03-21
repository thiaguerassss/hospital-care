package com.thiago.hospital_care.service.exception;

import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.CONFLICT)
public class DataBindingViolationException extends DataIntegrityViolationException {

    public DataBindingViolationException(String msg) { super(msg); }
}
