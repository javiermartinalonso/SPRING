package es.jmartin.ejemplos.spring.model.repository;

import java.util.List;

import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import es.jmartin.ejemplos.spring.model.dto.Provincia;

/**
 * Repository : Provincia.
 */
@Repository
public interface ProvinciaRepository extends PagingAndSortingRepository<Provincia, Integer> {

		List<Provincia> findAll();
}
