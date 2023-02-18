package com.dto.demo.service.impl;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.dto.demo.dto.PaiementDto;
import com.dto.demo.exception.ResourceNotFoundException;
import com.dto.demo.model.Consultation;
import com.dto.demo.model.Paiement;
import com.dto.demo.repository.ConsultationRepository;
import com.dto.demo.repository.PaiementRepository;
import com.dto.demo.service.PaiementService;

@Service
public class PaiementServiceImpl implements PaiementService{
	
	private final PaiementRepository paiementRepository;
	private final ConsultationRepository consultationRepository;
	
	@Autowired
	public PaiementServiceImpl(PaiementRepository paiementRepository, ConsultationRepository consultationRepository) {
		this.paiementRepository = paiementRepository;
		this.consultationRepository = consultationRepository;
	}

	@Override
	public PaiementDto save(PaiementDto paiementDto) {
		Paiement paiement = this.dtoToEntity(paiementDto);
		Paiement savedPaiement = this.paiementRepository.save(paiement);
		return new PaiementDto(savedPaiement);
	}

	@Override
	public PaiementDto findById(Long id) {
		Paiement paiement = this.paiementRepository
                .findById(id)
                .orElseThrow(() -> new ResourceNotFoundException(id));

        return new PaiementDto(paiement);
	}

	@Override
	public List<PaiementDto> findAll() {
		 return this.paiementRepository
	                .findAll()
	                .stream()
	                .map(paiement -> new PaiementDto(paiement))
	                .collect(Collectors.toList());
	}

	@Override
	public void deleteById(Long id) {
		this.paiementRepository.deleteById(id);
		
	}
	
	private Paiement dtoToEntity(PaiementDto paiementDto) {
		Paiement paiement = new Paiement();
		BeanUtils.copyProperties(paiementDto, paiement);
		
		Long consultationId = paiementDto.getConsultationId();
		if (consultationId != null) {
			Consultation consultation = this.consultationRepository.findById(consultationId).orElse(null);

			if (paiement != null) {
				paiement.setConsultation(consultation);
			}
		}
		return paiement;
	}

}
