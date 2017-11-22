package es.jmartin.example.rest.model.repository;

import java.util.List;

import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import es.jmartin.example.rest.model.dto.Pais;
import es.jmartin.example.rest.model.dto.Provincia;

/**
 * Repository : Pais.
 */
@Repository
public interface PaisRepository extends PagingAndSortingRepository<Pais, Integer> {

	List<Provincia> findListOfProvinciaByIdpais(Integer idpais);
	
}
