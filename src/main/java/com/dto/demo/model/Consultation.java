package com.dto.demo.model;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;
import lombok.Data;


@Table(name = "consultation")
@Entity
@Data
public class Consultation {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@Temporal(TemporalType.DATE)
	private Date date_consultation;
	
	private String context;
	private int consultation_count;
	private double price;
	private boolean status;
	
	@ManyToOne
	@JoinColumn(name = "person_id")
	  private Person person;
	
	@OneToMany(mappedBy = "consultation", cascade = CascadeType.ALL)
	private List<Paiement> paiements = new ArrayList<>();
	
}