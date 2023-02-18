package com.dto.demo.service.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.logging.Logger;
import java.util.stream.Collectors;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.dto.demo.dto.ConsultationDto;
import com.dto.demo.dto.PaiementDto;
import com.dto.demo.model.Consultation;
import com.dto.demo.model.Paiement;
import com.dto.demo.model.Person;
import com.dto.demo.repository.ConsultationRepository;
import com.dto.demo.repository.PersonRepository;
import com.dto.demo.service.ConsultationService;

@Service
public class ConsultationServiceImpl implements ConsultationService {

	private static final Logger LOGGER = Logger.getLogger(ConsultationServiceImpl.class.getName());

	private final ConsultationRepository consultationRepository;
	private final PersonRepository personRepository;

	@Autowired
	public ConsultationServiceImpl(ConsultationRepository consultationRepository, PersonRepository personRepository) {
		this.consultationRepository = consultationRepository;
		this.personRepository = personRepository;
	}
	
	
	@Override
	public List<ConsultationDto> findById(Long personId) {
	    Optional<Consultation> consultations = this.consultationRepository.findById(personId);
	    return consultations.stream().map(ConsultationDto::new).collect(Collectors.toList());
	}


	
	@Override
	public List<ConsultationDto> findAll() {
		 return this.consultationRepository
	                .findAll()
	                .stream()
	                .map(consultation -> new ConsultationDto(consultation))
	                .collect(Collectors.toList());
	}
	
	@Override
	public ConsultationDto save(ConsultationDto consultationDto) {
		Consultation consultation = this.dtoToEntity(consultationDto);
		Consultation savedConsultation = this.consultationRepository.save(consultation);
	    LOGGER.info("date is: " + savedConsultation.getDate_consultation());
		return new ConsultationDto(savedConsultation);
	}
	
	@Override
    public void deleteById(Long id) {
        this.consultationRepository.deleteById(id);
    }

	private Consultation dtoToEntity(ConsultationDto consultationDto) {
		Consultation consultation = new Consultation();
		List<Paiement> paiements = new ArrayList<>();
		
		BeanUtils.copyProperties(consultationDto, consultation);

		Long personId = consultationDto.getPersonId();
		if (personId != null) {
			Person person = this.personRepository.findById(personId).orElse(null);

			if (person != null) {
				consultation.setPerson(person);
			}
		}
		
		List<PaiementDto> paiementDtos = consultationDto.getPaiements();
		if (paiementDtos != null && paiementDtos.size() > 0) {
			paiementDtos.forEach(paiementDto -> {
				Paiement paiement = new Paiement();
				BeanUtils.copyProperties(paiementDto, paiement);
				paiement.setConsultation(consultation);
				paiements.add(paiement);
			});
		}
		consultation.setPaiements(paiements);
		return consultation;
	}
	
	public List<Consultation> dtoToEntities(List<ConsultationDto> consultationDtos, Long personId) {
	    if (consultationDtos == null) {
	        return null;
	    }

	    return consultationDtos.stream()
	            .map(consultationDto -> {
	                Consultation consultation = new Consultation();
	                BeanUtils.copyProperties(consultationDto, consultation);
	                if (personId != null) {
	        			Person person = this.personRepository.findById(personId).orElse(null);

	        			if (person != null) {
	        				consultation.setPerson(person);
	        			}
	        		}
	                return consultation;
	            })
	            .collect(Collectors.toList());
	}

}










