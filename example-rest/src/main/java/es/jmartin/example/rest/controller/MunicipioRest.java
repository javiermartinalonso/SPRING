package es.jmartin.example.rest.controller;

import static org.springframework.hateoas.mvc.ControllerLinkBuilder.linkTo;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.Link;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import es.jmartin.example.rest.model.dto.Municipio;
import es.jmartin.example.rest.model.repository.MunicipioRepository;

@RestController
@RequestMapping("/municipio")
public class MunicipioRest {

	@Autowired
	MunicipioRepository municipioRepository;

	@RequestMapping(value = "/{idMunicipio}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public Municipio findMunicipioById(@PathVariable Integer idMunicipio) {

		Municipio municipio = municipioRepository.findById(idMunicipio).orElse(null);

//		Link selfLink = linkTo(MunicipioRest.class).slash(municipio.getIdmunicipio()).withSelfRel();
//		municipio.add(selfLink);

		return municipio;
	}

	@RequestMapping(value = "/findall/{idProvincia}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public List<Municipio> findMunicipiosByIdProvincia(@PathVariable Integer idProvincia) {

		List<Municipio> listaMunicipios = municipioRepository.findAllByProvinciaIdProvincia(idProvincia);

//		for (Municipio municipio : listaMunicipios) {
//			Link selfLink = linkTo(MunicipioRest.class).slash(municipio.getIdmunicipio()).withSelfRel();
//			municipio.add(selfLink);
//		}

		return listaMunicipios;
	}

}
