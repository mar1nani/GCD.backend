package com.dto.demo.common.validator;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import com.dto.demo.dto.ConsultationDto;
import com.dto.demo.repository.ConsultationRepository;

@Component
public class ConsultationDtoValidator implements Validator {

    @Autowired
    private ConsultationRepository consultationRepository;

    @Override
    public boolean supports(Class<?> clazz) {
        return ConsultationDto.class.equals(clazz);
    }

    @Override
    public void validate(Object target, Errors errors) {
        ConsultationDto consultationDto = (ConsultationDto) target;

        // Check if there is already a consultation with the same date and person ID in the database
        if (consultationDto.getDate_consultation() != null && consultationDto.getPersonId() != null) {
            boolean exists = consultationRepository.existsByDateConsultationAndPersonId(consultationDto.getDate_consultation(), consultationDto.getPersonId());
            if (exists) {
                errors.reject("consultation.exists", "Une consultation existe déjà avec ce patient et cette date de consultation");
            }
        }
    }
}
