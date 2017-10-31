package es.jmartin.springbootwsclient.client;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.ws.client.core.support.WebServiceGatewaySupport;
import org.springframework.ws.soap.client.core.SoapActionCallback;

import es.jmartin.springbootwsclient.domain.wsdl.GetQuote;
import es.jmartin.springbootwsclient.domain.wsdl.GetQuoteResponse;

/**
 * Para crear un cliente de servicio web, simplemente tiene que extender la clase WebServiceGatewaySupport y codificar sus operaciones
 * 
 * 
 * @author javier.martin
 *
 */
public class QuoteClient extends WebServiceGatewaySupport {

	private static final Logger log = LoggerFactory.getLogger(QuoteClient.class);

	
	/**
	 * El cliente contiene un método: getQuote que realiza el intercambio real de SOAP.
	 * 
	 * En este método, tanto la clase GetQuote como las clases GetQuoteResponse se derivan del WSDL 
	 * y se generaron en el proceso de generación JAXB descrito en el paso anterior. 
	 * Crea el objeto GetQuote de solicitud y lo configura con el parámetro ticker. 
	 * Después de imprimir el código de cotización, usa WebServiceTemplate suministrado por la clase base WebServiceGatewaySupport de la que extendemos,
	 * para realizar el intercambio real del mensaje SOAP. Pasa el objeto request de tipo GetQuote, así como un objeto SoapActionCallback 
	 * para pasar un encabezado SOAPAction con la solicitud, ya que el WSDL describió que necesitaba este encabezado 
	 * en los elementos <soap:operation/>. Emite la respuesta en un objeto GetQuoteResponse, que luego se devuelve.
	 * @param ticker
	 * @return
	 */
	public GetQuoteResponse getQuote(String ticker) {

		GetQuote request = new GetQuote();
		request.setSymbol(ticker);

		log.info("Requesting quote for " + ticker);

		GetQuoteResponse response = (GetQuoteResponse) getWebServiceTemplate()
				.marshalSendAndReceive("http://www.webservicex.com/stockquote.asmx",
						request,
						new SoapActionCallback("http://www.webserviceX.NET/GetQuote"));

		return response;
	}

}
