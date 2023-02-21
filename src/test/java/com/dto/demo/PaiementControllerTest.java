package com.dto.demo;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.Matchers.hasSize;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.client.match.MockRestRequestMatchers.content;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.request;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.Arrays;
import java.util.Date;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultMatcher;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;

import com.dto.demo.controller.PaiementController;
import com.dto.demo.dto.PaiementDto;
import com.dto.demo.service.PaiementService;

@RunWith(SpringRunner.class)
@WebMvcTest(PaiementController.class)
public class PaiementControllerTest {

		@Autowired
	    private MockMvc mockMvc;

	    @MockBean
	    private PaiementService paiementService;
	    
	    

	    @Test
	    void testFindById() throws Exception {
	        Long id = 1L;
	        mockMvc.perform(MockMvcRequestBuilders.get("/paiements/{id}", id))
	                .andExpect(request().asyncStarted())
	                .andDo(MockMvcResultHandlers.log())
	                .andExpect(status().isOk())
	                .andExpect(jsonPath("$.id", is(1)));
	    }


	    @Test
	    public void testFindAll() throws Exception {
	        PaiementDto paiementDto1 = new PaiementDto();
	        paiementDto1.setId(1L);
	        paiementDto1.setDate_paiement(new Date());
	        paiementDto1.setPaid(100.0);
	        paiementDto1.setConsultationId(2L);

	        PaiementDto paiementDto2 = new PaiementDto();
	        paiementDto2.setId(2L);
	        paiementDto2.setDate_paiement(new Date());
	        paiementDto2.setPaid(200.0);
	        paiementDto2.setConsultationId(3L);

	        List<PaiementDto> paiements = Arrays.asList(paiementDto1, paiementDto2);

	        when(paiementService.findAll()).thenReturn(paiements);

	        mockMvc.perform(get("/api/paiement"))
	                .andExpect(status().isOk())
	                .andExpect(jsonPath("$", hasSize(2)))
	                .andExpect(jsonPath("$[0].id", is(1)))
	                .andExpect(jsonPath("$[0].date_paiement", is("2023-02-18")))
	                .andExpect(jsonPath("$[0].paid", is(100.0)))
	                .andExpect(jsonPath("$[0].consultationId", is(2)))
	                .andExpect(jsonPath("$[1].id", is(2)))
	                .andExpect(jsonPath("$[1].date_paiement", is("2023-02-18")))
	                .andExpect(jsonPath("$[1].paid", is(200.0)))
	                .andExpect(jsonPath("$[1].consultationId", is(3)));
	    }

	    @Test
	    public void testSave() throws Exception {
	        PaiementDto paiementDto = new PaiementDto();
	        paiementDto.setId(1L);
	        paiementDto.setDate_paiement(new Date());
	        paiementDto.setPaid(100.0);
	        paiementDto.setConsultationId(2L);

	        when(paiementService.save(paiementDto)).thenReturn(paiementDto);

	        mockMvc.perform(post("/api/paiement")
	                .contentType(MediaType.APPLICATION_JSON)
	                .content("{id: 1, date_paiement: '2023-02-18', paid: 100.0, consultationId: 2}"))
	                .andExpect(status().isOk())
	                .andExpect((ResultMatcher) content().json("{id: 1, date_paiement: '2023-02-18', paid: 100.0, consultationId: 2}"));
	    }

	    
		/*
		 * @Test public void testUpdate() throws Exception { PaiementDto paiementDto =
		 * new PaiementDto(); paiementDto.setId(1L); paiementDto.setDate_paiement(new
		 * Date()); paiementDto.setPaid(100.0); paiementDto.setConsultationId(2L);
		 * 
		 * when(paiementService.update(anyLong(),
		 * any(PaiementDto.class))).thenReturn(paiementDto);
		 * 
		 * mockMvc.perform(put("/api/paiement/1")
		 * .contentType(MediaType.APPLICATION_JSON)
		 * .content("{id: 1, date_paiement: '2023-02-18', paid: 200.0, consultationId: 2}"
		 * )) .andExpect(status().isOk()) .andExpect((ResultMatcher) content().
		 * json("{id: 1, date_paiement: '2023-02-18', paid: 100.0, consultationId: 2}"))
		 * ; }
		 */
	    
	    @Test
	    public void testDelete() throws Exception {
	        Long paiementId = 1L;
	        mockMvc.perform(delete("/api/paiement/" + paiementId))
	                .andExpect(status().isOk());
	        verify(paiementService, times(1)).deleteById(paiementId);
	    }


}
