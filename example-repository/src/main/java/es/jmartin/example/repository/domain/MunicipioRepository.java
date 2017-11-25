package es.jmartin.example.repository.domain;

import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import es.jmartin.domain.model.dto.Municipio;

/**
 * Repository : Municipio.
 */
@Repository
public interface MunicipioRepository extends PagingAndSortingRepository<Municipio, Integer> {

//	List<Municipio> findAllByProvinciaNombre(String nombreProvincia);
	
//	List<Municipio> findAllByProvinciaIdProvincia(Integer idProvincia);
}
