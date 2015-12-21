package spring_data_jpa_hibernate_h2;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import es.jmartin.ejemplos.spring.data.model.domainobject.Pais;
import es.jmartin.ejemplos.spring.data.model.repository.PaisRepository;

@RunWith(SpringJUnit4ClassRunner.class)

@ContextConfiguration("file:src/test/resources/spring/repository-context.xml")
public class PaisRepositoryTest
{

	@Autowired
	PaisRepository paisRepository;
	
	@Test
	public void testFindAll()
	{
//		assertEquals(1, paisRepository.findAll().size());
	}


	@Test
	public void testFindAllSort()
	{
		//TODO REVISAR
//		assertEquals(1, paisRepository.findAll());
	}


	@Test
	public void testFindAllIterableOfID()
	{
		//TODO REVISAR
//		assertEquals(1, paisRepository.findAll());
	}


	@Test
	public void testSaveIterableOfS()
	{
//		fail("Not yet implemented");
	}


	@Test
	public void testFlush()
	{
//		fail("Not yet implemented");
	}


	@Test
	public void testSaveAndFlush()
	{
//		fail("Not yet implemented");
	}


	@Test
	public void testDeleteInBatch()
	{
//		fail("Not yet implemented");
	}


	@Test
	public void testDeleteAllInBatch()
	{
//		fail("Not yet implemented");
	}


	@Test
	public void testGetOne()
	{
//		fail("Not yet implemented");
	}


	@Test
	public void testFindAllSort1()
	{
//		fail("Not yet implemented");
	}


	@Test
	public void testFindAllPageable()
	{
//		fail("Not yet implemented");
	}


	@Test
	public void testSaveS()
	{
		Pais pais = new Pais("Mi pais", "paisano", "idioma");
		
		paisRepository.save(pais);
				
		Pais paisBBDD = paisRepository.findOne((long) pais.getId());
		assertNotNull(paisBBDD);
		System.out.println(paisBBDD.getId());
	}


	@Test
	public void testSaveIterableOfS1()
	{
//		fail("Not yet implemented");
	}


	@Test
	public void testFindOne()
	{
//		fail("Not yet implemented");
	}


	@Test
	public void testExists()
	{
//		fail("Not yet implemented");
	}


	@Test
	public void testFindAll1()
	{
//		fail("Not yet implemented");
	}


	@Test
	public void testFindAllIterableOfID1()
	{
//		fail("Not yet implemented");
	}


	@Test
	public void testCount()
	{
//		assertEquals(1, paisRepository.count());
	}


	@Test
	public void testDeleteID()
	{
//		fail("Not yet implemented");
	}


	@Test
	public void testDeleteT()
	{
//		fail("Not yet implemented");
	}


	@Test
	public void testDeleteIterableOfQextendsT()
	{
//		fail("Not yet implemented");
	}


	@Test
	public void testDeleteAll()
	{
//		fail("Not yet implemented");
	}

}
