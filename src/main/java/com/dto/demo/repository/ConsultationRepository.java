package com.dto.demo.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.dto.demo.model.Consultation;

public interface ConsultationRepository extends JpaRepository<Consultation, Long>{

}
