package com.dto.demo.repository;

import org.springframework.data.repository.PagingAndSortingRepository;

import com.dto.demo.model.Person;

public interface PersonPaginationRepository extends PagingAndSortingRepository<Person, Long> {

}
