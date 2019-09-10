package es.satec.angolatelecom.sigo.sim.stock.service.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import es.satec.angolatelecom.sigo.sim.stock.service.dto.SIMControlFile;

@Repository
public interface SIMControlFileRepository extends CrudRepository<SIMControlFile, String> {
	public SIMControlFile findByBatch(String batch);
}