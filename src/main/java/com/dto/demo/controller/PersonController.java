package com.dto.demo.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.dto.demo.dto.PersonDto;
import com.dto.demo.service.PersonService;

@RestController
@RequestMapping("/api/person")
@CrossOrigin(origins = "http://localhost:4200")
public class PersonController {
  
  private final PersonService personService;
  
  @Autowired
  public PersonController(PersonService personService) {
    this.personService = personService;
  }
  
  @PostMapping
  public PersonDto save(@RequestBody PersonDto personDto) {
	  personDto.setId(null);
      return this.personService.save(personDto);
  }
  
  @GetMapping("/{id}")
  public PersonDto findById(@PathVariable Long id) {
      return this.personService.findById(id);
  }
  
  @GetMapping
  public List<PersonDto> findAll() {
      return this.personService.findAll();
  }
  
  @PutMapping
  public PersonDto update(@RequestBody PersonDto personDto) {
      return this.personService.save(personDto);
  }

  @DeleteMapping("{id}")
  public void deleteById(@PathVariable Long id) {
      this.personService.deleteById(id);
  }
  
  
  @GetMapping("/persons")
  public List<PersonDto> searchPersons(@RequestParam("name") String name) {
      return personService.findByName(name);
  }
}
