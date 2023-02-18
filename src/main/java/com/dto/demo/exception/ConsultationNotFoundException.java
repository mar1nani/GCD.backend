package com.dto.demo.exception;

public class ConsultationNotFoundException extends RuntimeException {
	private static final long serialVersionUID = 1L;

	public ConsultationNotFoundException(String message) {
	    super(message);
	}
}
