package com.dto.demo.service;

import java.util.List;

import com.dto.demo.dto.ConsultationDto;
import com.dto.demo.model.Consultation;

public interface ConsultationService {

    ConsultationDto save(ConsultationDto consultationDto);
    
    List<ConsultationDto> findById(Long personId);

    List<ConsultationDto> findAll();
    
    void deleteById(Long id);
    
    List<Consultation> dtoToEntities(List<ConsultationDto> consultationDtos, Long personId);


}
