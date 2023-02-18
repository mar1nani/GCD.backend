package com.dto.demo.common.exception;

import org.springframework.http.HttpStatus;

import lombok.Getter;

/**
 * This is a custom RuntimeException with the addition of a status
 * property. This property refers to a specific HttpStatus that we
 * want to return as a response, when a BaseRuntimeException
 * subclass is called.
 *
 * This helps us to send directly the status to the handler.
 * So, there is no need of different exception handler per
 * exception or per status code.
 */
@Getter
public class BaseRuntimeException extends RuntimeException {

    private static final long serialVersionUID = 1L;
	private HttpStatus status;

    public BaseRuntimeException(String message){
        super(message);
    }

    public BaseRuntimeException(String message, HttpStatus status){
        super(message);
        this.status = status;
    }
}
