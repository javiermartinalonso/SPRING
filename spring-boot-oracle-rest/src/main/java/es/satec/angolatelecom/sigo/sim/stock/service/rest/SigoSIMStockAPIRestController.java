package es.satec.angolatelecom.sigo.sim.stock.service.rest;

import java.io.IOException;

import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import es.satec.angolatelecom.sigo.sim.stock.service.dto.SIMControlFile;
import es.satec.angolatelecom.sigo.sim.stock.service.dto.SIMSerialNumbers;
import es.satec.angolatelecom.sigo.sim.stock.service.repository.SIMControlFileRepository;
import es.satec.angolatelecom.sigo.sim.stock.service.repository.SIMSerialNumbersRepository;

@RestController
public class SigoSIMStockAPIRestController extends AbstractRestController {

	@Autowired
	private SIMControlFileRepository simControlFileRepository;
	
	@Autowired
	private SIMSerialNumbersRepository simSerialNumbersRepository;	

	
	@RequestMapping(value = "/simControlFile", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
	public void updateClient(@RequestBody(required=true) SIMControlFile simControlFile, HttpServletResponse response) throws  IOException {
		try{
			log.debug("POST /simControlFile");
			
			simControlFileRepository.save(simControlFile);
			response.setStatus(HttpServletResponse.SC_CREATED);
		} catch(Exception e){
			log.error("[addSIMControlFile] Exception: " + e.toString(), e);
			response.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR, e.toString());
		}
	}	
	
	
	@RequestMapping(value = "/simSerialNumbers", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
	public void addClient(@RequestBody(required=true) SIMSerialNumbers simSerialNumbers, HttpServletResponse response) throws  IOException {
		try{
			log.debug("POST /simSerialNumbers");
			
			simSerialNumbersRepository.save(simSerialNumbers);
			response.setStatus(HttpServletResponse.SC_CREATED);
		} catch(Exception e){
			log.error("[addSIMSerialNumbers] Exception: " + e.toString(), e);
			response.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR, e.toString());
		}
	}		
	
}
