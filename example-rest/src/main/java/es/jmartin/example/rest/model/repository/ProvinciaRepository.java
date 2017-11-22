package es.jmartin.example.rest.model.repository;

import java.util.List;

import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import es.jmartin.example.rest.model.dto.Pais;
import es.jmartin.example.rest.model.dto.Provincia;

/**
 * Repository : Provincia.
 */
@Repository
public interface ProvinciaRepository extends PagingAndSortingRepository<Provincia, Integer> {

		List<Provincia> findAll();

		List<Provincia> findByPais(Pais pais);
}
