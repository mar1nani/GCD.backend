package com.dto.demo.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.dto.demo.dto.PaiementDto;
import com.dto.demo.service.PaiementService;

@RestController
@RequestMapping("/api/paiement")
@CrossOrigin(origins = "http://localhost:4200")
public class PaiementController {
	
	private final PaiementService paiementService;
	
	@Autowired
	public PaiementController(PaiementService paiementService) {
		this.paiementService = paiementService;
	}
	
	@GetMapping("/{id}")
    public PaiementDto findById(@PathVariable Long id) {
        return this.paiementService.findById(id);
    }
    
    @GetMapping
    public List<PaiementDto> findAll() {
        return this.paiementService.findAll();
    }
    
    @PostMapping
    public PaiementDto save(@RequestBody PaiementDto PaiementDto) {
    	PaiementDto.setId(null);
        return this.paiementService.save(PaiementDto);
    }
    
    @PutMapping
    public PaiementDto update(@RequestBody PaiementDto PaiementDto) {
        return this.paiementService.save(PaiementDto);
    }

    @DeleteMapping("{id}")
    public void deleteById(@PathVariable Long id) {
        this.paiementService.deleteById(id);
    }
}
