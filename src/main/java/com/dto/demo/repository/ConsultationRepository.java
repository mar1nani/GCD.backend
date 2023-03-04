package com.dto.demo.repository;

import java.util.Date;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.dto.demo.model.Consultation;

public interface ConsultationRepository extends JpaRepository<Consultation, Long>{

	@Query("SELECT COUNT(c) > 0 FROM Consultation c WHERE c.date_consultation = :dateConsultation AND c.person.id = :personId")
	boolean existsByDateConsultationAndPersonId(@Param("dateConsultation") Date dateConsultation, @Param("personId") Long personId);

}
