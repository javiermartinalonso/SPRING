package es.satec.angolatelecom.sigo.sim.stock.service.repository;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.fail;

import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit4.SpringRunner;

import es.satec.angolatelecom.sigo.sim.stock.service.SigoSIMStockServiceApplication;
import es.satec.angolatelecom.sigo.sim.stock.service.dto.SIMSerialNumbers;

@RunWith(SpringRunner.class)
//@FixMethodOrder(MethodSorters.NAME_ASCENDING)

//Configuracion standar h2 en memoria
//@DataJpaTest

//entorno de servlet simulado.
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.MOCK, classes = SigoSIMStockServiceApplication.class)

@AutoConfigureMockMvc
@TestPropertySource(locations = "classpath:application-oracle.properties")
public class SIMSerialNumbersRepositoryTest {

    @Autowired
	private SIMSerialNumbersRepository simSerialNumbersRepository;
    
	@Ignore
	@Test
	public void test() {
		fail("Not yet implemented");
	}

	@Test
	public void testSaveS()
	{		
		SIMSerialNumbers simSerialNumbers = 
				new SIMSerialNumbers("00001", "8963108180425000001");
		        
		simSerialNumbersRepository.save(simSerialNumbers);
//		entityManager.persist(simControlFile);
//	    entityManager.flush();
	    
		SIMSerialNumbers simSerialNumbersBBDD = simSerialNumbersRepository.findByBatch(simSerialNumbers.getBatch());
		assertNotNull(simSerialNumbersBBDD);
		System.out.println(simSerialNumbersBBDD.getBatch());
	}
}
