package com.dto.demo;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PersonService {
    @Autowired
    private PersonRepository personRepository;

    public PersonDTO createPerson(PersonDTO personDTO) {
        Person person = Person.builder()
                .name(personDTO.getName())
                .dob(personDTO.getDob())
                .address(personDTO.getAddress())
                .ville(personDTO.getVille())
                .gender(personDTO.getGender())
                .phoneNumber(personDTO.getPhoneNumber())
                .healthState(personDTO.getHealthState())
                .build();
        personRepository.save(person);
        return personDTO;
    }
    public List<Person> getAllPersons() {
        return personRepository.findAll();
    }
    public PersonDTO getPerson(long id) {
        //retrieve person from database and map to PersonDTO
        Person person = personRepository.findById(id).orElse(null);
        if(person == null)
            return null;
        return mapPersonToPersonDTO(person);
    }
    private PersonDTO mapPersonToPersonDTO(Person person) {
        return new PersonDTO(person.getId(),person.getName(), person.getDob(), person.getAddress(), person.getVille(), person.getGender(), person.getPhoneNumber(),person.getHealthState());
    }

    public PersonDTO updatePerson(long id, PersonDTO personDTO) {
        // retrieve person from database, update fields, and save
        Person person = personRepository.findById(id).orElse(null);
        if (person == null) {
            return null;
        }
        mapPersonDTOToPerson(personDTO, person);
        personRepository.save(person);
        return personDTO;
    }
    private void mapPersonDTOToPerson(PersonDTO personDTO, Person person) {
        person.setName(personDTO.getName());
        person.setDob(personDTO.getDob());
        person.setAddress(personDTO.getAddress());
        person.setVille(personDTO.getVille());
        person.setGender(personDTO.getGender());
        person.setPhoneNumber(personDTO.getHealthState());
    }
    public void deletePerson(long id) {
        //delete person from database
    	// Find the person by id
        Person person = personRepository.findById(id)
        		.orElseThrow(() -> new PersonNotFoundException("Person not found with id: " + id));
        // Delete the person from the database
        personRepository.delete(person);
    }
}

