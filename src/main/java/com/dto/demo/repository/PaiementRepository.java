package com.dto.demo.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.dto.demo.model.Paiement;

public interface PaiementRepository extends JpaRepository<Paiement, Long>{

}
