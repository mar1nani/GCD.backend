package com.dto.demo.dto;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.beans.BeanUtils;

import com.dto.demo.model.Consultation;
import com.dto.demo.model.Paiement;
import com.dto.demo.model.Person;

import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@Data
public class ConsultationDto {
	
	private Long id;
	private Date date_consultation;
	private String context;
	private int consultation_count;
	private double price;
	private boolean status;
	
	private Long personId;
	
	private List<PaiementDto> paiements = new ArrayList<>();

	public ConsultationDto(Consultation consultation) {
        BeanUtils.copyProperties(consultation, this, "personId");
        
        Person person = consultation.getPerson();
        if(person != null) {
        	this.personId = person.getId();
        }
        
        List<Paiement> paiements = consultation.getPaiements();
        if(paiements != null && paiements.size() > 0) {
        	paiements.forEach(paiement ->{
        		PaiementDto paiementDto = new PaiementDto(paiement);
        		this.paiements.add(paiementDto);
        	});
        }
	}
}
