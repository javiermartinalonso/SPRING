package es.satec.angolatelecom.sigo.sim.stock.service.rest;

import static org.junit.Assert.fail;
import static org.mockito.Mockito.when;

import java.util.Collections;
import java.util.Date;

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

import es.satec.angolatelecom.sigo.sim.stock.service.dto.SIMControlFile;
import es.satec.angolatelecom.sigo.sim.stock.service.repository.SIMControlFileRepository;

@RunWith(SpringRunner.class)
@WebMvcTest(SigoSIMStockAPIRestController.class)
public class SigoSIMStockAPIRestControllerTest {

	@Autowired
	private MockMvc mockMvc;
	
	@MockBean
	private SIMControlFileRepository simControlFileRepository;
	
	@Before
	public void initMocks() {
		MockitoAnnotations.initMocks(this);

        Date date = new Date();

		when(this.simControlFileRepository.findAll())
		.thenReturn( Collections.singletonList(
				new SIMControlFile("00001", "8963108180425000001", "8963108180425000010", 10, "TEST.CARD AGT00001.out", 
						date, null, null)));
		
		when(this.simControlFileRepository.findByBatch("00001"))
		.thenReturn(new SIMControlFile("00001", "8963108180425000001", "8963108180425000010", 10, "TEST.CARD AGT00001.out", 
		date, null, null));
	}
	
	@Test
	public void test() {
		fail("Not yet implemented");
	}
	
	@Test
	public void findAll() throws Exception{
				
		this.mockMvc.perform(MockMvcRequestBuilders.post("/simControlFile"))
		.andExpect(MockMvcResultMatchers.jsonPath("@.[0].id").value(1))
		.andExpect(MockMvcResultMatchers.jsonPath("@.[0].batch").value("00001"))
		.andExpect(MockMvcResultMatchers.content().contentType(MediaType.APPLICATION_JSON_UTF8))
		.andExpect(MockMvcResultMatchers.status().isOk());
	}	
	
	@Test
	public void testAddClientSIMControlFileHttpServletResponse() {
		fail("Not yet implemented");
	}

	@Test
	public void testAddClientSIMSerialNumbersHttpServletResponse() {
		fail("Not yet implemented");
	}

}
