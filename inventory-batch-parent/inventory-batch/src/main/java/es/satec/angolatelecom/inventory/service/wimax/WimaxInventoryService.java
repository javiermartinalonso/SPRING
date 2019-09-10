package es.satec.angolatelecom.inventory.service.wimax;

import java.util.List;
import java.util.stream.Collectors;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.stereotype.Service;

import es.satec.angolatelecom.inventory.domain.entities.Resource;
import es.satec.angolatelecom.inventory.dto.entities.cobre.Armario;
import es.satec.angolatelecom.inventory.dto.entities.common.NumeroDeTelefone;
import es.satec.angolatelecom.inventory.rest.client.OSSInventoryRestClient;
import es.satec.angolatelecom.inventory.service.mapper.InventoryResourceMapper;

@Service
public class WimaxInventoryService {

	private static final Logger log = LoggerFactory.getLogger(WimaxInventoryService.class);

	@Autowired
	private OSSInventoryRestClient ossInventoryRestClient;

	@Autowired
	private InventoryResourceMapper inventoryResourceMapper;

	public List<Armario> getAll() {
		log.debug("** getAll() **");

		List<Resource> list = ossInventoryRestClient.getResources("clase='Armario'",
				new ParameterizedTypeReference<List<Resource>>() {
				});

		return list.stream().map(resource -> inventoryResourceMapper.convertValue(resource, Armario.class))
				.collect(Collectors.toList());
	}

	public Resource get(String name) {
		log.debug("** get(name={}) **", name);

		Resource resource = ossInventoryRestClient.getResource(name, Resource.class);
		if (resource != null) {
			log.debug("telefono {} found", name);
			return inventoryResourceMapper.convertValue(resource, NumeroDeTelefone.class);
		}

		return null;
	}
	
/**	
	public List<Armario> creates(List<CreateCPERequest> list) {
		log.debug("** creates() ** {}", list);
		return list.stream().map(req -> create(req.toCPE())).collect(Collectors.toList());
	}

	public Armario create(Armario cpe) {
		log.debug("** create({}) **", cpe.getNome());

		Resource toSave = inventoryResourceMapper.convertToResource(cpe);
		Resource savedArmario = ossInventoryRestClient.createResource(toSave);

		log.debug("CPE {} successfully created", cpe.getNome());
		return inventoryResourceMapper.convertValue(savedArmario, Armario.class);
	}
*/
	public void delete(String name) {
		log.debug("** delete({}) **", name);
		ossInventoryRestClient.deleteResource(name);
		log.debug("CPE {} successfully deleted", name);
	}

}