package com.dto.demo.service.impl;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.dto.demo.dto.PersonDto;
import com.dto.demo.exception.ResourceNotFoundException;
import com.dto.demo.model.Person;
import com.dto.demo.repository.PersonRepository;
import com.dto.demo.service.ConsultationService;
import com.dto.demo.service.PersonService;

@Service
public class PersonServiceImpl implements PersonService {

	private final PersonRepository personRepository;
	private final ConsultationService consultationService;

	
	@Autowired
	public PersonServiceImpl(PersonRepository personRepository, ConsultationService consultationService) {
		this.personRepository = personRepository;
		this.consultationService = consultationService;
	}

	public PersonDto save(PersonDto personDto) {
		Person person = this.dtoToEntity(personDto);
		Person savedPerson = this.personRepository.save(person);
		return new PersonDto(savedPerson);
	}

	public PersonDto findById(Long id) {
		Person person = this.personRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException(id));

		return new PersonDto(person);
	}

	@Override
	public void deleteById(Long id) {
		this.personRepository.deleteById(id);
	}

	@Override
	public List<PersonDto> findAll() {
		return this.personRepository.findAll().stream().map(person -> new PersonDto(person))
				.collect(Collectors.toList());
	}
	
	@Override
	public List<PersonDto> findByName(String name) {
        return personRepository.findByNameContainingIgnoreCase(name).stream().map(person -> new PersonDto(person))
        		.collect(Collectors.toList());
	}
	
	private Person dtoToEntity(PersonDto personDto) {
        Person person = new Person();
        BeanUtils.copyProperties(personDto, person);
        person.setConsultations(this.consultationService.dtoToEntities(personDto.getConsultations(), person.getId()));
        return person;
    }

}
