package com.dto.demo.service;

import java.util.List;

import com.dto.demo.dto.PaiementDto;

public interface PaiementService {
	PaiementDto save(PaiementDto consultationDto);
    
	PaiementDto findById(Long id);

    List<PaiementDto> findAll();
    
    void deleteById(Long id);
}
