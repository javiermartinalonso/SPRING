package es.jmartin.example.rest;

import static org.mockito.Mockito.when;

import java.util.Collections;
import java.util.Optional;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import es.jmartin.example.rest.model.dto.Pais;
import es.jmartin.example.rest.model.repository.MunicipioRepository;
import es.jmartin.example.rest.model.repository.PaisRepository;
import es.jmartin.example.rest.model.repository.ProvinciaRepository;

@RunWith(SpringRunner.class)
@WebMvcTest

public class PaisRestTest {
	
	@Autowired
	private MockMvc mockMvc;
	
	@MockBean
	private PaisRepository paisRepository;
	
	@MockBean
	MunicipioRepository municipioRepository;
	
	@MockBean
	ProvinciaRepository provinciaRepository;
	
	
	@Before
	public void initMocks() {
		MockitoAnnotations.initMocks(this);

		when(this.paisRepository.findAll())
		.thenReturn( Collections.singletonList(new Pais(73, "España", null)));
		
		when(this.paisRepository.findById(73))
		.thenReturn(Optional.of(new Pais(73, "España", null)));
	}
	
	@Test
	public void findAll() throws Exception{
		
//		Mockito.when(this.paisRepository.findAll())
//		.thenReturn( Collections.singletonList(new Pais(73, "España", null)));
		
		this.mockMvc.perform(MockMvcRequestBuilders.get("/pais/findall"))
		.andExpect(MockMvcResultMatchers.jsonPath("@.[0].idpais").value(73))
		.andExpect(MockMvcResultMatchers.jsonPath("@.[0].nombre").value("España"))
		.andExpect(MockMvcResultMatchers.content().contentType(MediaType.APPLICATION_JSON_UTF8))
		.andExpect(MockMvcResultMatchers.status().isOk());
	}
	
	
	@Test
	public void findById() throws Exception{
		this.mockMvc.perform(MockMvcRequestBuilders.get("/pais/73"))
		.andExpect(MockMvcResultMatchers.jsonPath("@.idpais").value(73))
		.andExpect(MockMvcResultMatchers.jsonPath("@.nombre").value("España"))
		.andExpect(MockMvcResultMatchers.content().contentType(MediaType.APPLICATION_JSON_UTF8))
		.andExpect(MockMvcResultMatchers.status().isOk());
	}

}
