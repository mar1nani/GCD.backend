package com.dto.demo.exception;

import org.springframework.http.HttpStatus;

import com.dto.demo.common.exception.BaseRuntimeException;

public class ResourceNotFoundException extends BaseRuntimeException {

    private static final long serialVersionUID = 1L;

	public ResourceNotFoundException(Long id) {
        super("Resource with " + id + " not found", HttpStatus.NOT_FOUND);
    }
}
