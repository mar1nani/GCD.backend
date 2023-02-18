package com.dto.demo.service;

import java.util.List;

import com.dto.demo.dto.PersonDto;

public interface PersonService {

    PersonDto findById(Long id);

    PersonDto save(PersonDto personDto);

    List<PersonDto> findAll();
    
    void deleteById(Long id);
    
    List<PersonDto> findByName(String name);
}
