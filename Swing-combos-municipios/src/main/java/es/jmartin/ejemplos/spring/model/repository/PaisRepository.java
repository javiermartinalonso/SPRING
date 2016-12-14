package es.jmartin.ejemplos.spring.model.repository;

import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import es.jmartin.ejemplos.spring.model.dto.Pais;

/**
 * Repository : Pais.
 */
@Repository
public interface PaisRepository extends PagingAndSortingRepository<Pais, Integer> {

}
