package es.jmartin.example.repository.domain;

import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import es.jmartin.domain.model.dto.Person;

/**
 * Repository : Persona.
 */
@Repository
public interface PersonaRepository extends PagingAndSortingRepository<Person, Integer> {	
}
