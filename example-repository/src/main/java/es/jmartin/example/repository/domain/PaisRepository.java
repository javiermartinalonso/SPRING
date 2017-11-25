package es.jmartin.example.repository.domain;

import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import es.jmartin.domain.model.dto.Pais;

/**
 * Repository : Pais.
 */
@Repository
public interface PaisRepository extends PagingAndSortingRepository<Pais, Integer> {

//	List<Provincia> findListOfProvinciaByIdpais(Integer idpais);
	
}
