package es.jmartin.java.endpoint;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ws.server.endpoint.annotation.Endpoint;
import org.springframework.ws.server.endpoint.annotation.PayloadRoot;
import org.springframework.ws.server.endpoint.annotation.RequestPayload;
import org.springframework.ws.server.endpoint.annotation.ResponsePayload;

import es.jmartin.java.repository.CountryRepository;
import io.spring.guides.gs_producing_web_service.GetCountryRequest;
import io.spring.guides.gs_producing_web_service.GetCountryResponse;


//@Endpoint registra la clase con Spring WS como posible candidato para procesar los mensajes SOAP entrantes.
@Endpoint
public class CountryEndPoint {
	private static final String NAMESPACE_URI = "http://spring.io/guides/gs-producing-web-service";

	private CountryRepository countryRepository;

	@Autowired
	public CountryEndPoint(CountryRepository countryRepository) {
		this.countryRepository = countryRepository;
	}

	//@PayloadRootluego es utilizado por Spring WS para elegir el método del manejador basado en el espacio de nombres del mensaje y localPart .
	@PayloadRoot(namespace = NAMESPACE_URI, localPart = "getCountryRequest")
	//@RequestPayload indica que el mensaje entrante se correlacionará con el requestparámetro del método .
	//@ResponsePayload anotación hace que Spring WS mapee el valor devuelto a la carga de respuesta.
	@ResponsePayload
	public GetCountryResponse getCountry(@RequestPayload GetCountryRequest request) {
		GetCountryResponse response = new GetCountryResponse();
		response.setCountry(countryRepository.findCountry(request.getName()));

		return response;
	}
}