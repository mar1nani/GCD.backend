package com.dto.demo.dto;

import java.util.Date;

import org.springframework.beans.BeanUtils;

import com.dto.demo.model.Consultation;
import com.dto.demo.model.Paiement;
import com.fasterxml.jackson.annotation.JsonFormat;

import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@Data
public class PaiementDto {

	private Long id;
	
    @JsonFormat(pattern = "yyyy-MM-dd")
	private Date date_paiement;
    
	private double paid;

	private Long consultationId;
	
	public PaiementDto(Paiement paiement) {
        BeanUtils.copyProperties(paiement, this, "personId");

        Consultation consultation = paiement.getConsultation();
        if(consultation != null) {
        	this.consultationId = consultation.getId();
        }
	}
}
