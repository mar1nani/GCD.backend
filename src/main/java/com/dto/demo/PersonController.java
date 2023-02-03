package com.dto.demo;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/person")
public class PersonController {
    @Autowired
    private PersonService personService;
    
    @PostMapping
    public PersonDTO createPerson(@RequestBody PersonDTO personDTO) {
        return personService.createPerson(personDTO);
    }
    @GetMapping("/") 
    public ResponseEntity<List<Person>> getAllPersons() {
    	List<Person> persons = personService.getAllPersons(); 
    	return new ResponseEntity<>(persons, HttpStatus.OK); 
    }
    @GetMapping("/{id}")
    public PersonDTO getPerson(@PathVariable("id") long id) {
        return personService.getPerson(id);
    }
    @PutMapping("/{id}")
    public PersonDTO updatePerson(@PathVariable("id") long id, @RequestBody PersonDTO personDTO) {
        return personService.updatePerson(id, personDTO);
    }
    @DeleteMapping("/{id}")
    public ResponseEntity<String> deletePerson(@PathVariable("id") long id) {
        personService.deletePerson(id);
        return new ResponseEntity<>("Person with id: " + id + " has been successfully deleted.", HttpStatus.OK);
    }
}
