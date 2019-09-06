package es.satec.angolatelecom.sigo.sim.stock.service.repository;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.fail;

import java.util.Date;

import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit4.SpringRunner;

import es.satec.angolatelecom.sigo.sim.stock.service.SigoSIMStockServiceApplication;
import es.satec.angolatelecom.sigo.sim.stock.service.dto.SIMControlFile;

//@FixMethodOrder(MethodSorters.NAME_ASCENDING)
@RunWith(SpringRunner.class)

//Configuracion standar h2 en memoria
//@DataJpaTest

//entorno de servlet simulado.
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.MOCK, classes = SigoSIMStockServiceApplication.class)

@AutoConfigureMockMvc
@TestPropertySource(locations = "classpath:application-oracle.properties")
public class SIMControlFileRepositoryTest {
	
//    @Autowired
//    private TestEntityManager entityManager;
    
    @Autowired
	private SIMControlFileRepository simControlFileRepository;
		
    @Ignore
	@Test
	public void test() {
		fail("Not yet implemented");
	}
	

	@Test
	public void testSaveS()
	{
		Date date = new Date();
		
		SIMControlFile simControlFile = 
				new SIMControlFile("00001", "8963108180425000001", 
						"8963108180425000010", 10, "TEST.CARD AGT00001.out", 
						date, null, null);
		        
		simControlFileRepository.save(simControlFile);
//		entityManager.persist(simControlFile);
//	    entityManager.flush();
	    
		SIMControlFile simControlFileBBDD = simControlFileRepository.findByBatch(simControlFile.getBatch());
		assertNotNull(simControlFileBBDD);
		System.out.println(simControlFileBBDD.getBatch());
	}
}
