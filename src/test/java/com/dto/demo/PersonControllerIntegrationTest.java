package com.dto.demo;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.notNullValue;
import static org.hamcrest.Matchers.greaterThanOrEqualTo;
import static org.hamcrest.Matchers.hasSize;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import org.junit.Before;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import com.dto.demo.controller.PersonController;
import com.dto.demo.dto.PersonDto;
import com.dto.demo.enums.Gender;
import com.dto.demo.service.PersonService;
import com.fasterxml.jackson.databind.ObjectMapper;

@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
@AutoConfigureMockMvc
public class PersonControllerIntegrationTest {

    @Autowired
    private MockMvc mockMvc;

    @Before
    public void setUp() {
    	PersonService personServiceMock = Mockito.mock(PersonService.class);
    	PersonController personController = new PersonController(personServiceMock);
    	mockMvc = MockMvcBuilders.standaloneSetup(personController).build();
    }
    
    @Test
    public void testSave() throws Exception {
        PersonDto personDto = new PersonDto();
        personDto.setName("John");
        personDto.setDob("06/09/2023");
        personDto.setAddress("1 Avenue Pasteur");
        personDto.setVille("Paris");
        personDto.setGender(Gender.MALE);
        personDto.setPhoneNumber("0698757205");
        personDto.setHealthState("RAS");

        String json = new ObjectMapper().writeValueAsString(personDto);

        mockMvc.perform(post("/api/person")
                .contentType(MediaType.APPLICATION_JSON)
                .content(json))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id", is(notNullValue())));
    }

    @Test
    public void testFindById() throws Exception {
        mockMvc.perform(get("/api/person/1"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id", is(1)));
    }

    @Test
    public void testFindAll() throws Exception {
        mockMvc.perform(get("/api/person"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", hasSize(greaterThanOrEqualTo(0))));
    }

    @Test
    public void testUpdate() throws Exception {
        PersonDto personDto = new PersonDto();
        personDto.setName("John");
        personDto.setDob("06/09/2023");
        personDto.setAddress("1 Avenue Pasteur");
        personDto.setVille("Paris");
        personDto.setGender(Gender.MALE);
        personDto.setPhoneNumber("0698757205");
        personDto.setHealthState("RAS");

        String json = new ObjectMapper().writeValueAsString(personDto);

        mockMvc.perform(put("/api/person")
                .contentType(MediaType.APPLICATION_JSON)
                .content(json))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id", is(1)));
    }

    @Test
    public void testDeleteById() throws Exception {
        mockMvc.perform(delete("/api/person/1"))
                .andExpect(status().isNoContent());

        mockMvc.perform(get("/api/person/1"))
                .andExpect(status().isNotFound());
    }

    @Test
    public void testSearchPersons() throws Exception {
        mockMvc.perform(get("/api/person/persons?name=John"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", hasSize(greaterThanOrEqualTo(0))));
    }
}
