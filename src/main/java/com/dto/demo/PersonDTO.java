package com.dto.demo;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@Builder
@NoArgsConstructor
public class PersonDTO {

	private Long id;
	private String name;
    private String dob;
    private String address;
    private String ville;
    private Gender gender;
    private String phoneNumber;
    private String healthState;
    
}
