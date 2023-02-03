package com.dto.demo;

import org.springframework.data.repository.PagingAndSortingRepository;

public interface PersonPaginationRepository extends PagingAndSortingRepository<Person, Long> {

}
