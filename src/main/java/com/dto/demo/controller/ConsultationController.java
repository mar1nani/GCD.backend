package com.dto.demo.controller;

import java.util.Collections;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.dto.demo.common.validator.ConsultationDtoValidator;
import com.dto.demo.dto.ConsultationDto;
import com.dto.demo.exception.BadRequestException;
import com.dto.demo.service.ConsultationService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/consultation")
@CrossOrigin(origins = "http://localhost:4200")
public class ConsultationController {

	private final ConsultationService consultationService;
	private final ConsultationDtoValidator consultationDtoValidator;

	@Autowired
	public ConsultationController(ConsultationService consultationService,
			ConsultationDtoValidator consultationDtoValidator) {
		this.consultationService = consultationService;
		this.consultationDtoValidator = consultationDtoValidator;
	}

	@GetMapping("/{id}")
	public List<ConsultationDto> findById(@PathVariable Long id) {
		return this.consultationService.findById(id);
	}

	@GetMapping
	public List<ConsultationDto> findAll() {
		return this.consultationService.findAll();
	}

	@PostMapping
	public ResponseEntity<?> save(@Valid @RequestBody ConsultationDto consultationDto, Errors errors) {
	    consultationDtoValidator.validate(consultationDto, errors);
	    if (errors.hasErrors()) {
	        return ResponseEntity.badRequest().body(errors.getAllErrors());
	    }

	    try {
	        consultationDto.setId(null);
	        ConsultationDto savedConsultationDto = consultationService.save(consultationDto);
	        return ResponseEntity.ok(savedConsultationDto);
	    } catch (BadRequestException ex) {
	        return ResponseEntity.badRequest().body(Collections.singletonMap("error", ex.getMessage()));
	    } catch (Exception ex) {
	        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(Collections.singletonMap("error", ex.getMessage()));
	    }
	}


	@PutMapping
	public ConsultationDto update(@RequestBody ConsultationDto consultationDto) {
		return this.consultationService.save(consultationDto);
	}

	@DeleteMapping("{id}")
	public void deleteById(@PathVariable Long id) {
		this.consultationService.deleteById(id);
	}
}
