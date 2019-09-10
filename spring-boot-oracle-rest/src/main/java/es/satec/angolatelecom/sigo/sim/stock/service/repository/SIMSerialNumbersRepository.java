package es.satec.angolatelecom.sigo.sim.stock.service.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import es.satec.angolatelecom.sigo.sim.stock.service.dto.SIMSerialNumbers;

@Repository
public interface SIMSerialNumbersRepository extends CrudRepository<SIMSerialNumbers, String> {
	public SIMSerialNumbers findByBatch(String batch);
}