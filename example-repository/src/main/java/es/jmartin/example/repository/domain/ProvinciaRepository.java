package es.jmartin.example.repository.domain;

import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import es.jmartin.domain.model.dto.Provincia;

/**
 * Repository : Provincia.
 */
@Repository
public interface ProvinciaRepository extends PagingAndSortingRepository<Provincia, Integer> {

//		List<Provincia> findAll();

//		List<Provincia> findByPais(Pais pais);
}
