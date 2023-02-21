package com.dto.demo;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import com.dto.demo.controller.ConsultationController;
import com.dto.demo.dto.ConsultationDto;
import com.dto.demo.dto.PaiementDto;
import com.dto.demo.service.ConsultationService;
import com.fasterxml.jackson.databind.ObjectMapper;

@WebMvcTest(ConsultationController.class)
public class ConsultationControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private ConsultationService consultationService;

    @Test
    public void testFindAll() throws Exception {
        // Arrange
        List<ConsultationDto> expectedConsultations = new ArrayList<>();
        expectedConsultations.add(createConsultationDto());
        when(consultationService.findAll()).thenReturn(expectedConsultations);

        // Act
        mockMvc.perform(MockMvcRequestBuilders.get("/api/consultation"))
               .andExpect(MockMvcResultMatchers.status().isOk())
               .andExpect(MockMvcResultMatchers.content().contentType(MediaType.APPLICATION_JSON))
               .andExpect(MockMvcResultMatchers.jsonPath("$[0].id").value(expectedConsultations.get(0).getId()));

        // Assert
        verify(consultationService).findAll();
    }

    @Test
    public void testFindById() throws Exception {
        // Arrange
        Long consultationId = 1L;
        ConsultationDto expectedConsultation = createConsultationDto();
        when(consultationService.findById(consultationId)).thenReturn(List.of(expectedConsultation));

        // Act
        mockMvc.perform(MockMvcRequestBuilders.get("/api/consultation/" + consultationId))
               .andExpect(MockMvcResultMatchers.status().isOk())
               .andExpect(MockMvcResultMatchers.content().contentType(MediaType.APPLICATION_JSON))
               .andExpect(MockMvcResultMatchers.jsonPath("$[0].id").value(expectedConsultation.getId()));

        // Assert
        verify(consultationService).findById(consultationId);
    }

    @Test
    public void testSave() throws Exception {
        // Arrange
        ConsultationDto expectedConsultation = createConsultationDto();
        when(consultationService.save(any(ConsultationDto.class))).thenReturn(expectedConsultation);

        // Act
        mockMvc.perform(MockMvcRequestBuilders.post("/api/consultation")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(asJsonString(expectedConsultation)))
               .andExpect(MockMvcResultMatchers.status().isOk())
               .andExpect(MockMvcResultMatchers.content().contentType(MediaType.APPLICATION_JSON))
               .andExpect(MockMvcResultMatchers.jsonPath("$.id").value(expectedConsultation.getId()));

        // Assert
        verify(consultationService).save(expectedConsultation);
    }

    @Test
    public void testUpdate() throws Exception {
        // Arrange
        ConsultationDto expectedConsultation = createConsultationDto();
        when(consultationService.save(any(ConsultationDto.class))).thenReturn(expectedConsultation);

        // Act
        mockMvc.perform(MockMvcRequestBuilders.put("/api/consultation")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(asJsonString(expectedConsultation)))
               .andExpect(MockMvcResultMatchers.status().isOk());

        // Assert
        verify(consultationService).save(expectedConsultation);
    }

    
    @Test
    public void testDelete() throws Exception {
        // Arrange
        Long consultationId = 1L;
        doNothing().when(consultationService).deleteById(anyLong());

        // Act
        mockMvc.perform(MockMvcRequestBuilders.delete("/api/consultation/" + consultationId))
               .andExpect(MockMvcResultMatchers.status().isOk());

        // Assert
        verify(consultationService).deleteById(consultationId);
    }

    private ConsultationDto createConsultationDto() {
        ConsultationDto consultationDto = new ConsultationDto();
        consultationDto.setId(1L);
        consultationDto.setDate_consultation(new Date());
        consultationDto.setContext("Test context");
        consultationDto.setConsultation_count(1);
        consultationDto.setPrice(50.0);
        consultationDto.setStatus(true);
        consultationDto.setPersonId(1L);
        List<PaiementDto> paiements = new ArrayList<>();
        PaiementDto paiementDto = new PaiementDto();
        paiementDto.setId(1L);
        paiementDto.setPaid(50.0);
        paiementDto.setDate_paiement(new Date(1640995200000L)); // 1640995200000L is the Unix timestamp for 01-01-
        paiements.add(paiementDto);
        consultationDto.setPaiements(paiements);
        return consultationDto;
    }


    private static String asJsonString(final Object obj) {
        try {
            final ObjectMapper mapper = new ObjectMapper();
            final String jsonContent = mapper.writeValueAsString(obj);
            return jsonContent;
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
   }

}

