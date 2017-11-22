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
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import es.jmartin.example.rest.model.dto.Pais;
import es.jmartin.example.rest.model.dto.Provincia;
import es.jmartin.example.rest.model.repository.MunicipioRepository;
import es.jmartin.example.rest.model.repository.PaisRepository;
import es.jmartin.example.rest.model.repository.ProvinciaRepository;

@RestController
@RequestMapping("/pais")
public class PaisRest {

	@Autowired
	PaisRepository paisRepository;
	
	@Autowired	
	ProvinciaRepository provinciaRepository;
	
	@Autowired
	MunicipioRepository municipioRepository;

	@RequestMapping(value = "/findall", method= RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public Iterable<Pais> findAll() {
//		return paisRepository.findAll();
		
//		return municipioRepository.findAll();
		
		
		
		
		Iterable<Pais> listaPaises = paisRepository.findAll();
	        
	    
	    for (Pais pais : listaPaises) {
	        Link selfLink = linkTo(PaisRest.class).slash(pais.getIdpais()).withSelfRel();
	        pais.add(selfLink);
	        
	        if (provinciaRepository.findByPais(pais).size() > 0) {
	            List<Provincia> methodLinkBuilder = 
	              methodOn(PaisRest.class).findProvinciaByPaisIdPais(pais.getIdpais());
	            Link provinciasLink = linkTo(methodLinkBuilder).withRel("allProvinvias");
	            pais.add(provinciasLink);
	        }
	    }
	    
	    return listaPaises;
	}

	@RequestMapping(value = "/{idPais}", method= RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public Pais findById(@PathVariable Integer idPais) {
	    
		Pais pais = paisRepository.findById(idPais).orElse(null);
		
        Link selfLink = linkTo(PaisRest.class).slash(pais.getIdpais()).withSelfRel();
        pais.add(selfLink);
        
	    return pais;
	}
	

	@RequestMapping(value = "/provincia/{idProvincia}", method= RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public Provincia findProvinciaById(@PathVariable Integer idProvincia) {
	    
		Provincia provincia = provinciaRepository.findById(idProvincia).orElse(null);
		
        Link selfLink = linkTo(PaisRest.class).slash("/provincia" + provincia.getIdprovincia()).withSelfRel();
        provincia.add(selfLink);
        
	    return provincia;
	}
	
	@RequestMapping(value = "/{idPais}/provincias", method= RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public List<Provincia> findProvinciaByPaisIdPais(@PathVariable Integer idPais) {
    
		List<Provincia> listaProvincias = provinciaRepository.findByPais(paisRepository.findById(idPais).orElse(null));
		
		 for (Provincia provincia : listaProvincias) {
	        Link selfLink = linkTo(PaisRest.class).slash(provincia.getIdprovincia()).withSelfRel();
	        provincia.add(selfLink);
	        
	        
	        Provincia methodLinkBuilder = 
		              methodOn(PaisRest.class).findProvinciaById(provincia.getIdprovincia());
		            Link provinciasLink = linkTo(methodLinkBuilder).withRel("allProvinvias");
		            provincia.add(provinciasLink);
		 }
		 		 		 
	    return listaProvincias;
	}
	
	@RequestMapping(value = "/hola", method= RequestMethod.GET)
	public @ResponseBody String hola() {
		return "hola Mundo";
	}
}
