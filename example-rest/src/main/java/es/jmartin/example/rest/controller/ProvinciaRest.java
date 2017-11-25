package es.jmartin.example.rest.controller;

import static org.springframework.hateoas.mvc.ControllerLinkBuilder.linkTo;
import static org.springframework.hateoas.mvc.ControllerLinkBuilder.methodOn;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.Link;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import es.jmartin.example.rest.model.dto.Municipio;
import es.jmartin.example.rest.model.dto.Provincia;
import es.jmartin.example.rest.model.repository.MunicipioRepository;
import es.jmartin.example.rest.model.repository.PaisRepository;
import es.jmartin.example.rest.model.repository.ProvinciaRepository;

@RestController
@RequestMapping("/provincia")
public class ProvinciaRest {

	@Autowired
	PaisRepository paisRepository;
	
	@Autowired	
	ProvinciaRepository provinciaRepository;
	
	@Autowired
	MunicipioRepository municipioRepository;
	
	@RequestMapping(value = "/{idProvincia}", method= RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public Provincia findProvinciaById(@PathVariable Integer idProvincia) {
	    
		Provincia provincia = provinciaRepository.findById(idProvincia).orElse(null);
		
		// Link selfLink =
		// linkTo(ProvinciaRest.class).slash(provincia.getIdprovincia()).withSelfRel();
		// provincia.add(selfLink);
		//
		//
		//
		// if (municipioRepository.findAllByProvinciaIdProvincia(idProvincia).size() >
		// 0) {
		// List<Municipio> methodLinkBuilder =
		// methodOn(MunicipioRest.class).findMunicipiosByIdProvincia(idProvincia);
		//
		// Link municipiosLink = linkTo(methodLinkBuilder).withRel("allMunicipios");
		// provincia.add(municipiosLink);
		// }
      
        
	    return provincia;
	}

	
	@RequestMapping(value = "/findAll/{idPais}", method= RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public List<Provincia> findProvinciasByIdPais(@PathVariable Integer idPais) {
	
		List<Provincia> listaProvincias = provinciaRepository.findByPais(paisRepository.findById(idPais).orElse(null));
		
		for (Provincia provincia : listaProvincias) {
			
	        Link selfLink = linkTo(ProvinciaRest.class).slash(provincia.getIdprovincia()).withSelfRel();
	        provincia.add(selfLink);
	        
	    	//TODO no queremos devolver la lista de municipios
	        provincia.setListOfMunicipio(null);
	    	
	        if (municipioRepository.findAllByProvinciaIdProvincia(provincia.getIdprovincia()).size() > 0) {
	            List<Municipio> methodLinkBuilder = 
	              methodOn(MunicipioRest.class).findMunicipiosByIdProvincia(provincia.getIdprovincia());
	            
	            Link municipiosLink = linkTo(methodLinkBuilder).withRel("allMunicipios");
	            provincia.add(municipiosLink);
	        }
	    }        
		
		return listaProvincias;
	}
}
