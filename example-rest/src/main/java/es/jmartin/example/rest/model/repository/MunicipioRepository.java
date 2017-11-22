package es.jmartin.example.rest.model.repository;

import java.util.List;

import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import es.jmartin.example.rest.model.dto.Municipio;

/**
 * Repository : Municipio.
 */
@Repository
public interface MunicipioRepository extends PagingAndSortingRepository<Municipio, Integer> {

	List<Municipio> findAllByProvinciaNombre(String nombreProvincia);
	
	List<Municipio> findAllByProvinciaIdProvincia(Integer idProvincia);
}
