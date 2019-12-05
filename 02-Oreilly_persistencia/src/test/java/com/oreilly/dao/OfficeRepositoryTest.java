package com.oreilly.dao;



import static org.hamcrest.CoreMatchers.not;
import static org.hamcrest.collection.IsIterableContainingInAnyOrder.containsInAnyOrder;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertThat;
import static org.junit.Assert.assertTrue;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

import com.oreilly.entities.Officer;
import com.oreilly.entities.Rank;


@SuppressWarnings({"SqlNoDataSourceInspection", "SqlResolve"})
@SpringBootTest
@RunWith(SpringRunner.class)
/**
 * En una clase de prueba como esta, Spring interpretará que eso significa que cada prueba debe ejecutarse 
 * en una transacción que se revierte al final de la prueba . 
 * Eso evitará que la base de datos de prueba se vea afectada por las pruebas y mantendrá las pruebas en sí mismas, todas independientes
 * @author javier.martin
 *
 */
@Transactional
public class OfficeRepositoryTest {
	
	@Autowired
	private OfficerRepository dao;
	
	@Autowired
	private JdbcTemplate template;
	
	private RowMapper<Integer> idMapper = (rs, num) -> rs.getInt("id");
	
	@Test
	public void testSave() {
	    Officer officer = new Officer(Rank.LIEUTENANT, "Nyota", "Uhuru");
	    officer = dao.save(officer);
	    assertNotNull(officer.getId());
	}
	
	@Test
	public void findOneThatExists() {
	    template.query("select id from officers", idMapper)
	    .forEach(id -> {
	    	Optional<Officer> officer = dao.findById(id);
		    assertTrue(officer.isPresent());
		    assertEquals(id, officer.get().getId());
	    });
	}
	
	@Test
	public void count() {
	    assertEquals(5, dao.count());
	}
	
	@Test
	public void findAll() {
	    List<String> dbNames = dao.findAll().stream()
	            .map(Officer::getLast)
	            .collect(Collectors.toList());
	    assertThat(dbNames, containsInAnyOrder("Kirk", "Picard", "Sisko", "Janeway", "Archer"));
	}

	@Test
	public void delete() {
	    template.query("select id from officers", idMapper)
	    .forEach(id -> {
	    	Optional<Officer> officer = dao.findById(id);
		    assertTrue(officer.isPresent());
		    dao.delete(officer.get());
	    });		
	   
	    assertEquals(0, dao.count());
	}

	@Test
	public void existsById() {
	    IntStream.rangeClosed(1, 5)
	            .forEach(id -> assertTrue(String.format("%d should exist", id), dao.existsById(id)));
	    
	    template.query("select id from officers", idMapper)
	    .forEach(id -> assertTrue(dao.existsById(id)));			    
	}
	
	@Test
	public void doesNotExists() throws Exception {
	    List<Integer> ids = template.query("select id from officers", idMapper);

	    assertThat(ids, not(containsInAnyOrder(999)));	 
	    assertFalse(dao.existsById(999));
	}
	
	@Test
	public void findByLast()
	{
		List<Officer> kirks = dao.findByLast("Kirk");
		assertEquals(1, kirks.size());
		assertEquals("Kirk", kirks.get(0).getLast());
	}
	
	@Test
	public void findAllByRankAndLastLike(){
	    List<Officer> officers = dao.findAllByRankAndLastLike(Rank.CAPTAIN, "%i%");
	    System.out.println(officers);
	    List<String> lastNames = officers.stream()
	    		.map(Officer::getLast)
	    		.collect(Collectors.toList());
	    assertThat(lastNames, containsInAnyOrder("Kirk", "Picard", "Sisko"));
	}
	
}
