package com.dto.demo.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.dto.demo.dto.ConsultationDto;
import com.dto.demo.service.ConsultationService;

@RestController
@RequestMapping("/api/consultation")
public class ConsultationController {

    private final ConsultationService consultationService;

    @Autowired
    public ConsultationController(ConsultationService consultationService) {
        this.consultationService = consultationService;
    }
    
    @GetMapping("/{id}")
    public List<ConsultationDto> findById(@PathVariable Long id) {
        return this.consultationService.findById(id);
    }
    
    @GetMapping
    public List<ConsultationDto> findAll() {
        return this.consultationService.findAll();
    }
    
    @PostMapping
    public ConsultationDto save(@RequestBody ConsultationDto consultationDto) {
    	consultationDto.setId(null);
        return this.consultationService.save(consultationDto);
    }
    
    @PutMapping
    public ConsultationDto update(@RequestBody ConsultationDto consultationDto) {
        return this.consultationService.save(consultationDto);
    }

    @DeleteMapping("{id}")
    public void deleteById(@PathVariable Long id) {
        this.consultationService.deleteById(id);
    }
}
