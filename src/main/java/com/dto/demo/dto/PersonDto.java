package com.dto.demo.dto;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.BeanUtils;

import com.dto.demo.enums.Gender;
import com.dto.demo.model.Consultation;
import com.dto.demo.model.Person;

import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@Data
public class PersonDto {

	private Long id;
	private String name;
	private String dob;
	private String address;
	private String ville;
	private Gender gender;
	private String phoneNumber;
	private String healthState;

	private List<ConsultationDto> consultations = new ArrayList<>();

	public PersonDto(Person person) {
		BeanUtils.copyProperties(person, this, "consultations");

		List<Consultation> consultations = person.getConsultations();
		if (consultations != null && consultations.size() > 0) {
			consultations.forEach(consultation -> {
				ConsultationDto consultationDto = new ConsultationDto(consultation);
				this.consultations.add(consultationDto);
			});
		}
	}

}
