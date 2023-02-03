package com.dto.demo;

import java.io.Serializable;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.NOT_FOUND)
public class PersonNotFoundException extends RuntimeException implements Serializable{
	private static final long serialVersionUID = 1L;

	public PersonNotFoundException(String message) {
        super(message);
    }
}
