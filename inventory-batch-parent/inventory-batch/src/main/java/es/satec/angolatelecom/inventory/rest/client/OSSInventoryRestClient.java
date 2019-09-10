package es.satec.angolatelecom.inventory.rest.client;

import java.io.UnsupportedEncodingException;
import java.net.URISyntaxException;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.apache.commons.lang3.ArrayUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.utils.URIBuilder;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.message.BasicNameValuePair;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.client.ClientHttpRequestFactory;
import org.springframework.http.client.HttpComponentsClientHttpRequestFactory;
import org.springframework.stereotype.Component;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;

import com.fasterxml.jackson.annotation.JsonAutoDetect;

import es.satec.angolatelecom.inventory.domain.entities.Booking;
import es.satec.angolatelecom.inventory.domain.entities.Relation;
import es.satec.angolatelecom.inventory.domain.entities.Resource;
import es.satec.angolatelecom.inventory.domain.entities.Service;
import es.satec.angolatelecom.inventory.dto.entities.ResourceLocation;
import es.satec.angolatelecom.inventory.dto.entities.ResourceLocation.LocationItem;
import es.satec.angolatelecom.inventory.exception.InventarioBatchException;

@Component
public class OSSInventoryRestClient implements InitializingBean {

	private static final String CHILDREN = "children";

	private static final String LOCATIONS = "locations";

	private static final String RESOURCES2 = "/resources";

	private static final String SERVICES = "/services/";

	private static final String SERVICE = "/service/";

	private static final String BOOKING2 = "/booking/";

	private static final String SERVICE_NAME = "serviceName";

	private static final String BOOKING_ID = "bookingId";

	private static final String BOOKING = "/booking";

	private static final String NO_RESULTS_FOUND = "No results found";

	private static final String LOCATION_NOT_FOUND = "Location not found";

	private static final String RESOURCES = "/resources/";

	private static final String RESOURCE_NAME = "resourceName";

	private static final Logger log = LoggerFactory.getLogger(OSSInventoryRestClient.class);

	private RestTemplate restTemplate = new RestTemplate(getClientHttpRequestFactory());

	private ClientHttpRequestFactory getClientHttpRequestFactory() {
		int timeout = 600000;
		RequestConfig config = RequestConfig.custom().setConnectTimeout(timeout).setConnectionRequestTimeout(timeout)
				.setSocketTimeout(timeout).build();
		CloseableHttpClient client = HttpClientBuilder.create().setDefaultRequestConfig(config).build();
		return new HttpComponentsClientHttpRequestFactory(client);
	}

	public RestTemplate getRestTemplate() {
		return restTemplate;
	}

	@Override
	public void afterPropertiesSet() throws Exception {
		restTemplate = new RestTemplate(getClientHttpRequestFactory());
	}

	@Value("${inventory.oss.endpoint.uri}")
	private String ossInventoryURI;

	public List<ResourceLocation> location(String resourceName) {
		try {
			Map<String, String> uriVars = new HashMap<>();
			uriVars.put(RESOURCE_NAME, resourceName);

			ResponseEntity<List<ResourceLocation>> rateResponse = getRestTemplate().exchange(
					ossInventoryURI + RESOURCES + encodeURIComponent(resourceName) + "/location", HttpMethod.GET, null,  new ParameterizedTypeReference<List<ResourceLocation>> () {});

			return rateResponse.getBody();
		} catch (HttpClientErrorException e) {
			if (e.getStatusCode() == HttpStatus.NOT_FOUND) {
				log.debug(LOCATION_NOT_FOUND, e);
				return new ArrayList<>();
			}
			// Rethrow
			throw e;
		}
	}

	public <T> List<T> queryDistance(String resourceType, Double latitude, Double longitude, Double distance,
			ParameterizedTypeReference<List<T>> responseType) {

		@JsonAutoDetect
		class DistanceQuery {
			@SuppressWarnings("unused")
			public Double distanceNumeric;
			@SuppressWarnings("unused")
			public Double longitude;
			@SuppressWarnings("unused")
			public Double latitude;
			@SuppressWarnings("unused")
			public String orientClass;
		}

		try {
			DistanceQuery query = new DistanceQuery();
			query.distanceNumeric = distance;
			query.latitude = latitude;
			query.longitude = longitude;
			query.orientClass = resourceType;

			ResponseEntity<List<T>> rateResponse = getRestTemplate().exchange(ossInventoryURI + "/queryDistance",
					HttpMethod.POST, new HttpEntity<Object>(query), responseType);

			return rateResponse.getBody();
		} catch (HttpClientErrorException e) {
			if (e.getStatusCode() == HttpStatus.NOT_FOUND) {
				log.debug(NO_RESULTS_FOUND, e);
				return new ArrayList<>();
			}
			// Rethrow
			throw e;
		}
	}
	
	
	public <T> List<T> namedQuery(String queryName, List<BasicNameValuePair> parameterList,
			ParameterizedTypeReference<List<T>> responseType) {
		URIBuilder builder;
		try {
			builder = new URIBuilder(ossInventoryURI + "/query/" + queryName + "/traverse");

			if (parameterList != null) {
				parameterList.stream().forEach(p -> builder.addParameter(p.getName(), p.getValue()));
			}
			
			return clientRestGet(responseType, builder);
		} catch (URISyntaxException e) {
			log.error("The url: " + ossInventoryURI + "/query/" + queryName + "/traverse" + "is incorrect.", e);
			throw new InventarioBatchException(e);
		} catch (HttpClientErrorException e) {
			if (e.getStatusCode().equals(HttpStatus.NOT_FOUND)) {
				log.debug(NO_RESULTS_FOUND, e);
				return new ArrayList<>();
			}
			// Rethrow
			throw e;
		}
		
	}

	/**
	 * @param parameterList
	 * @param responseType
	 * @param builder
	 * @return
	 * @throws RestClientException
	 * @throws URISyntaxException
	 */
	private <T> List<T> clientRestGet(ParameterizedTypeReference<List<T>> responseType, URIBuilder builder)
					throws URISyntaxException {

		ResponseEntity<List<T>> rateResponse = getRestTemplate().exchange(builder.build(), HttpMethod.GET, null,
				responseType);

		return rateResponse.getBody();
	}

	
	public <T> List<T> namedQuery(String queryName, Map<String, String> parameters,
			ParameterizedTypeReference<List<T>> responseType) {
		return namedQuery(queryName, parameters.entrySet().stream().map(e-> new BasicNameValuePair(e.getKey(), e.getValue())).collect(Collectors.toList()), responseType);
	}

	public <T> List<T> getResources(String filter, ParameterizedTypeReference<List<T>> responseType) {

		// Hay que intentar trabajar con la informacion de paginado... Page<T>
		// no se como van las cabeceras pero hay que trabajar con eso..
		URIBuilder builder;
		try {
			builder = new URIBuilder(ossInventoryURI + RESOURCES2);
			builder.addParameter("page", "0");
			builder.addParameter("filter", filter);

			
			return clientRestGet(responseType, builder);	
		} catch (URISyntaxException e) {
			log.error("The url: " + ossInventoryURI + RESOURCES2 + "is incorrect.", e);
			throw new InventarioBatchException(e);
		} catch (HttpClientErrorException e) {
			if (e.getStatusCode().equals(HttpStatus.NOT_FOUND)) {
				log.debug(NO_RESULTS_FOUND, e);
				return new ArrayList<>();
			}
			// Rethrow
			throw e;
		}		
	}

	public <T> T getResource(String resourceName, Class<T> resourceClass) {
		try {
			return getRestTemplate().getForObject(ossInventoryURI + RESOURCES + URLEncoder.encode(resourceName, "UTF-8"), resourceClass);
		} catch (HttpClientErrorException e) {
			if (e.getStatusCode().equals(HttpStatus.NOT_FOUND)) {
				log.debug(NO_RESULTS_FOUND, e);
				return null;
			}
			// Rethrow
			throw e;
		} catch (RestClientException e) {
			throw e;
		} catch (UnsupportedEncodingException e) {
			throw new InventarioBatchException(e.getMessage());
		}
	}
	
	public Resource createResource(Resource resource) {
		return getRestTemplate().postForEntity(ossInventoryURI + RESOURCES2, resource, Resource.class).getBody();
	}
	
	public Resource updateResource(Resource resource) {
		
		String url = ossInventoryURI + RESOURCES+encodeURIComponent( (String) resource.getProperty("nome"));
		ResponseEntity<Resource> response = getRestTemplate().exchange( url, 
				HttpMethod.PUT, 
				new HttpEntity<Resource>(resource),
				Resource.class);
		
		return response.getBody();
	}
	
	public boolean createResources(List<Resource> resourcesToSave) {
		@JsonAutoDetect
		class BulkResources {
			@SuppressWarnings("unused")
			public List<Object> relation = new ArrayList<>();
			
			@SuppressWarnings("unused")
			public List<Resource> resource;
		}
		
		BulkResources bulkResources = new BulkResources();
		bulkResources.resource = resourcesToSave;
		
		ResponseEntity<Object> response  = getRestTemplate().postForEntity(ossInventoryURI + "/resources/bulk", bulkResources, Object.class);		
		return response.getStatusCode()==HttpStatus.CREATED;
	}
	
	public void createRelations(String sourceResourceName, String relationName, List<String> targetResourceName) {
		HttpEntity<List<String>> requestEntity = new HttpEntity<>(targetResourceName);		
		getRestTemplate().exchange(ossInventoryURI + "/resources/bulk/"+encodeURIComponent(sourceResourceName)+"/"+encodeURIComponent(relationName), HttpMethod.POST, requestEntity, Object.class);
	}
	
	public List<Booking> listBookings() {
		// Hay que intentar trabajar con la informacion de paginado... Page<T>
		// no se como van las cabeceras pero hay que trabajar con eso..
		URIBuilder builder;
		try {
			builder = new URIBuilder(ossInventoryURI + BOOKING);
			builder.addParameter("page", "0");

			ResponseEntity<List<Booking>> rateResponse = getRestTemplate().exchange(builder.build(), HttpMethod.GET,
					null, new ParameterizedTypeReference<List<Booking>>() {
					});

			return rateResponse.getBody();
		} catch (URISyntaxException e) {
			throw new InventarioBatchException(e);
		} catch (HttpClientErrorException e) {
			if (e.getStatusCode().equals(HttpStatus.NOT_FOUND)) {
				log.debug(NO_RESULTS_FOUND, e);
				return new ArrayList<>();
			}
			// Rethrow
			throw e;
		}
	}
	
	public Booking getBooking(String bookingId) {
		
		Map<String, String> uriParameters = new HashMap<>();
		uriParameters.put(BOOKING_ID, bookingId);
		ResponseEntity<Booking> response = getRestTemplate().getForEntity(ossInventoryURI + BOOKING2+encodeURIComponent(bookingId), Booking.class);
		
		return response.getBody();
	}
	
	/**
	 * Obtener los recursos asociados a una reserva...
	 * @param bookingId
	 * @return
	 */
	public List<Resource> getBookingResources(String bookingId) {
		
		Map<String, String> uriParameters = new HashMap<>();
		uriParameters.put(BOOKING_ID, bookingId);
		
		ResponseEntity<List<Resource>> response = getRestTemplate().exchange(ossInventoryURI + BOOKING2+encodeURIComponent(bookingId)+RESOURCES2, HttpMethod.GET, null,
				new ParameterizedTypeReference<List<Resource>>() {});
		
		
		return response.getBody();
	}
	
	/**
	 * La reserva se hace con el provisioningId como bookingId
	 * En la descripcion es necesario aportar toda la informacion que podamos para describir
	 * la reserva... Intentaremos que se pueda buscar en la descripcion
	 * @param bookingId
	 * @param description
	 * @return
	 */
	public Booking createBooking(String bookingId, String description) {
		Booking create = new Booking();
		create.setName(bookingId);
		create.setDescription(description);
		
		ResponseEntity<Booking> response  = getRestTemplate().postForEntity(ossInventoryURI + BOOKING ,create, Booking.class);
		
		return response.getBody();
	}
	
	/**
	 * Reserva el conjunto de recursos de inventario con un bookingId
	 * @param bookingId
	 * @return
	 */
	public Booking createBooking(String bookingId, String tag, List<String> resourceNames) {
		
		
		URIBuilder builder;
		try {
			builder = new URIBuilder(ossInventoryURI + BOOKING2+encodeURIComponent(bookingId)+"/bulk_resources");
			builder.addParameter("tag", tag);
			
			ResponseEntity<Booking> response  = getRestTemplate().postForEntity(builder.build(), resourceNames, Booking.class);
			return response.getBody();
			
		} catch (URISyntaxException e) {
			throw new InventarioBatchException(e);
		}
	}
	
	/**
	 * Libera la reserva de un conjunto de recursos
	 * @param bookingId
	 * @return
	 */
	public void cancelBooking(String bookingId, List<String> resourceNames) {
		HttpEntity<List<String>> requestEntity = new HttpEntity<>(resourceNames);		
		getRestTemplate().exchange(ossInventoryURI + BOOKING2+encodeURIComponent(bookingId)+"/bulk_resources", HttpMethod.DELETE, requestEntity, Booking.class);
	}
	
	/**
	 * La reserva se hace con el provisioningId como bookingId
	 * En la descripcion es necesario aportar toda la informacion que podamos para describir
	 * la reserva... Intentaremos que se pueda buscar en la descripcion
	 * @param bookingId
	 * @param description
	 * @return
	 */
	public Booking createBooking(String bookingId, String code, String description) {
		Booking create = new Booking();
		create.setName(bookingId);
		create.setCode(code);
		create.setDescription(description);
		
		ResponseEntity<Booking> response  = getRestTemplate().postForEntity(ossInventoryURI + BOOKING ,create, Booking.class);
		
		return response.getBody();
	}
	
	/**
	 * Borra la reserva desasociado todos los recursos/relaciones asociados a la reserva...
	 * @param bookingId
	 */
	public void deleteBooking(String bookingId) {
		
		Map<String, String> uriParameters = new HashMap<>();
		uriParameters.put(BOOKING_ID, bookingId);
		getRestTemplate().delete(ossInventoryURI + BOOKING2+encodeURIComponent(bookingId));
		
	}
	
	/**
	 * Cancelar una reserva: desasocia pero no borra la reserva
	 * @param bookingId
	 */
	public void cancelBooking(String bookingId) {
		
		Map<String, String> uriParameters = new HashMap<>();
		uriParameters.put(BOOKING_ID, bookingId);
		getRestTemplate().delete(ossInventoryURI + BOOKING2+encodeURIComponent(bookingId)+"/cancel");
		
	}
	
	public Booking bookResource(String bookingId, String resourceName) {
		
		Booking result = null;
		
		Map<String, String> uriParameters = new HashMap<>();
		uriParameters.put(BOOKING_ID, bookingId);
		uriParameters.put(RESOURCE_NAME, resourceName);
		
		try {
			//esto lanza un 409 en el caso de que ya este reservado....
			//404 en caso de que no exista el booking o el recurso....
			//y otro error no controlable 500
			ResponseEntity<Booking> response  = getRestTemplate().postForEntity(ossInventoryURI + BOOKING2+encodeURIComponent(bookingId)+RESOURCES+encodeURIComponent(resourceName), 
					null, Booking.class);
			
			result = response.getBody();
			
		} catch (HttpClientErrorException e) {
			//Si es 409 entonces el recurso ya esta reservado ...
			//Otros caso son un error...
			switch (e.getStatusCode().value()) {
			case 409:
			case 404:
				break;
			default:
				throw e;
			}
		}
		
		return result;
	}
	
	public void unBookResource(String bookingId, String resourceName) {

		Map<String, String> uriParameters = new HashMap<>();
		uriParameters.put(BOOKING_ID, bookingId);
		uriParameters.put(RESOURCE_NAME, resourceName);
		getRestTemplate().delete(ossInventoryURI + BOOKING2+encodeURIComponent(bookingId)+RESOURCES+encodeURIComponent(resourceName));
	}
	
	public void moveToService(String bookingId, String serviceId) {
		Map<String, String> uriParameters = new HashMap<>();
		uriParameters.put(BOOKING_ID, bookingId);
		uriParameters.put(SERVICE_NAME, serviceId);
		
		ResponseEntity<Booking> response  = getRestTemplate().postForEntity(ossInventoryURI + BOOKING2+encodeURIComponent(bookingId)+SERVICE+encodeURIComponent(serviceId), null, Booking.class);
		
		//Entiendo que sera 200 siempre y no se lanzara una exception...
		response.getStatusCode();
	}
	
	public Service getService(String serviceName) {
		
		Map<String, String> uriParameters = new HashMap<>();
		uriParameters.put(SERVICE_NAME, serviceName);
		
		ResponseEntity<Service> response = getRestTemplate().exchange(ossInventoryURI + SERVICES+encodeURIComponent(serviceName), HttpMethod.GET, null,
				new ParameterizedTypeReference<Service>() {});
		
		return response.getBody();
		
	}

	public List<Resource> getServiceResources(String serviceName) {
		Map<String, String> uriParameters = new HashMap<>();
		uriParameters.put(SERVICE_NAME, serviceName);
		
		ResponseEntity<List<Resource>> response = getRestTemplate().exchange(ossInventoryURI + SERVICES+encodeURIComponent(serviceName)+RESOURCES2, HttpMethod.GET, null,
				new ParameterizedTypeReference<List<Resource>>() {});
		
		return response.getBody();
	}

	public void addResourceToService(String serviceName, String resourceName) {
		
		
		Map<String, String> uriParameters = new HashMap<>();
		uriParameters.put(SERVICE_NAME, serviceName);
		uriParameters.put(RESOURCE_NAME, resourceName);
	
		try {
		
			ResponseEntity<Service> response = getRestTemplate().exchange(ossInventoryURI + SERVICES+encodeURIComponent(serviceName)+RESOURCES+encodeURIComponent(resourceName), 
					HttpMethod.POST, 
					null,
					new ParameterizedTypeReference<Service>() {});
						
			response.getBody();

		} catch (HttpClientErrorException e) {
			//Si es 409 entonces el recurso ya esta en un service ...
			//Otros caso son un error...
			switch (e.getStatusCode().value()) {
			case 409:
			case 404:
				break;
			default:
				throw e;
			}
		}
		
	}

	public void removeResourceFromService(String serviceName, String resourceName) {
		Map<String, String> uriParameters = new HashMap<>();
		uriParameters.put(SERVICE_NAME, serviceName);
		uriParameters.put(RESOURCE_NAME, resourceName);
		
		getRestTemplate().delete(ossInventoryURI + SERVICES+encodeURIComponent(serviceName)+RESOURCES+encodeURIComponent(resourceName));
		
	}

	public void deleteService(String serviceName) {
		Map<String, String> uriParameters = new HashMap<>();
		uriParameters.put(SERVICE_NAME, serviceName);
		getRestTemplate().delete(ossInventoryURI + SERVICES+encodeURIComponent(serviceName));
	}

	public void migrate(String oldBookingId, String newBookingId) {
		Map<String, String> uriParameters = new HashMap<>();
		uriParameters.put("oldBookingId", oldBookingId);
		uriParameters.put("newBookingId", newBookingId);
		
		getRestTemplate().put(ossInventoryURI + BOOKING2+encodeURIComponent(oldBookingId)+"/migrate/"+encodeURIComponent(newBookingId), null);
	}

	public es.satec.angolatelecom.inventory.domain.entities.Service createService(String idService, String servicaName, String idCustomer, String nameCustomer) {
		Service create = new Service();
		create.setIdService(idService);
		create.setNameService(servicaName);
		create.setIdCustomer(idCustomer);
		create.setNameCustomer(nameCustomer);
						
		return getRestTemplate().postForEntity(ossInventoryURI + "/services", create, Service.class).getBody();
	}
	
	
	public String getRelatedInResourceName (String targetName, String relationName) {
		String resourceName = null;
		
		List<Relation> relations = getRelations(null, targetName, relationName);
		
		if (relations.size()>1)
			throw new InventarioBatchException("Too many results");
		
		if (relations.size()==1)
			resourceName = (relations.get(0).getSource());
		
		return resourceName;
	}


	public String getRelatedOutResourceName (String targetName, String relationName) {
		//Deberia hacer lo mismo que el metodo anterior, pero intercambiando los parámetros,
		//hay que revisar la duplicidad de codigo de este método con el anterior getRelatedInResourceName
		throw new InventarioBatchException("not implemented yet!!!");
	}
	
	
	public List<Relation> getRelations (String sourceName, String targetName, String relationName) {
		
		URIBuilder relationsURI;
		try {
			relationsURI = new URIBuilder(ossInventoryURI + "/relations/filter");
			
			relationsURI.addParameter("relationshipName", 	relationName);
			if (sourceName!=null)
				relationsURI.addParameter("sourceClassName", 	sourceName);
			
			if (targetName!=null)
				relationsURI.addParameter("destinyClassName", 	targetName);

			ResponseEntity<List<Relation>> relationsListResponse = getRestTemplate().exchange(relationsURI.build(), HttpMethod.GET, null, new ParameterizedTypeReference<List<Relation>> () {});
			return relationsListResponse.getBody();
		} catch (URISyntaxException e) {
			throw new InventarioBatchException(e);
		} catch (HttpClientErrorException e) {
			if (e.getStatusCode() == HttpStatus.NOT_FOUND) {
				log.debug(NO_RESULTS_FOUND, e);
				return new ArrayList<>();
			} else
				throw e;
		}
	}
	
	public static String encodeURIComponent(String s) {
	    String result;

	    try {
	        result = URLEncoder.encode(s, "UTF-8")
	                .replaceAll("\\+", "%20")
	                .replaceAll("\\%21", "!")
	                .replaceAll("\\%27", "'")
	                .replaceAll("\\%28", "(")
	                .replaceAll("\\%29", ")")
	                .replaceAll("\\%7E", "~");
	    } catch (UnsupportedEncodingException e) {
	        result = s;
	    }

	    return result;
	}

	public LocationItem findLocationItemByNamePath(String... names) {
		
		return findLocationItemPathByNamePath(names).stream().reduce((first, second) -> second).orElse(null);
		
	}
	
	public List<LocationItem> findLocationItemPathByNamePath(String... names) {
		List<LocationItem> resolvedLocationItems = new ArrayList<>();
		
		
	    for(String name : names) {
			
				//Buscamos las localizaciones que resultan de resolver la ruta acumulada
	    		LocationItem parentFound = findLocationItemsByCodePath( resolvedLocationItems.stream().map(LocationItem::getCode).collect(Collectors.toList()).toArray(new String[0]) );
	    		LocationItem found = null;
	    		
				if (parentFound != null) {
					found = parentFound.getItems().stream().filter(i-> compareLocationNames(i.getName(), name)
							).findFirst().orElse(null);
				}
				
				if (found == null) {
					return resolvedLocationItems;
				}
				
				resolvedLocationItems.add(found);
		}
	
		return resolvedLocationItems;
		
	}
	
	
	
	/**
	 * TODO Solución concreta de Angola. Aplicarlo sólo si estamos en entorno de Angola.
	 * No es código CORE.
	 * Compara nombres de provincias en Angola que solemos encontrar escritas de diferente forma.
	 * 
	 * @param name1
	 * @param name2
	 * @return
	 */
	private boolean compareLocationNames(String name1, String name2) {
		
		String n1 = name1 != null ? removeAcute(name1).replaceAll("[^\\p{L}\\p{Nd}]+", " ").toLowerCase().trim() : null;
		String n2 = name2 != null ? removeAcute(name2).replaceAll("[^\\p{L}\\p{Nd}]+", " ").toLowerCase().trim() : null;
		
		if((n1 == null && n2 != null) || (n1 != null && n2 == null)) {
			return false;
		} else if (n1 == null){
			return true;
		}
		
		return StringUtils.equals(n1, n2) 
				|| StringUtils.equals( n1.replaceAll("c", "K") , n2 ) 
				|| StringUtils.equals( n2.replaceAll("c", "K") , n1 )
				|| StringUtils.equals( n1.replaceAll("w", "u") , n2 ) 
				|| StringUtils.equals( n2.replaceAll("w", "u") , n1 )
				|| StringUtils.equals( n1.replaceAll("c", "K").replaceAll("w", "u") , n2 ) 
				|| StringUtils.equals( n2.replaceAll("c", "K").replaceAll("w", "u") , n1 )
				;
	}
	
	public String removeAcute(String input) {
	    // Cadena de caracteres original a sustituir.
	    String original = "áàäéèëíìïóòöúùuñÁÀÄÉÈËÍÌÏÓÒÖÚÙÜÑçÇ";
	    // Cadena de caracteres ASCII que reemplazarán los originales.
	    String ascii = "aaaeeeiiiooouuunAAAEEEIIIOOOUUUNcC";
	    String output = input;
	    for (int i=0; i<original.length(); i++) {
	        // Reemplazamos los caracteres especiales.
	        output = output.replace(original.charAt(i), ascii.charAt(i));
	    }//for i
	    return output;
	}//remove1
	
	public LocationItem findLocationItemsByCodePath(String... codes) {
		LocationItem result = null;
		
		try {
			
			String searchURL = String.join("/", ArrayUtils.addAll(new String[] {ossInventoryURI, LOCATIONS}, codes));
			
			URIBuilder builder = new URIBuilder(searchURL);
			builder.addParameter(CHILDREN, "true");
			
			if (codes != null && codes.length > 0) {
				ResponseEntity<LocationItem> singleResponse = getRestTemplate().exchange(
						builder.build(), HttpMethod.GET, null,  new ParameterizedTypeReference<LocationItem> () {});

				result = singleResponse.getBody();
				
			} else {
				ResponseEntity<List<LocationItem>> multipleResponse = getRestTemplate().exchange(
						builder.build(), HttpMethod.GET, null,  new ParameterizedTypeReference<List<LocationItem>> () {});

				List<LocationItem> items = multipleResponse.getBody();

				LocationItem i = new LocationItem();
				i.setItems(items);
				
				result = i;
			}
			
			
			return result;
		} catch (HttpClientErrorException e) {
			if (e.getStatusCode() == HttpStatus.NOT_FOUND) {
				log.debug(LOCATION_NOT_FOUND, e);
				return null;
			}
			// Rethrow
			throw e;
		} catch (URISyntaxException urie){
			throw new InventarioBatchException("Error getting location by code cannot build url", urie);
		}
	}
	
	public ResourceLocation getLocation(ResourceLocation resurceLocation) {
		ResourceLocation result = null;
		
		try {
			
			String searchURL = String.join("/", ossInventoryURI, LOCATIONS, 
					resurceLocation.getCountry().getCode(),
					resurceLocation.getProvince().getCode(),
					resurceLocation.getRegion().getCode(),
					resurceLocation.getTown().getCode(),
					resurceLocation.getCode()
					);
			
			URIBuilder builder = new URIBuilder(searchURL);
			builder.addParameter(CHILDREN, "true");
			
			ResponseEntity<ResourceLocation> singleResponse = getRestTemplate().exchange(
					builder.build(), HttpMethod.GET, null,  new ParameterizedTypeReference<ResourceLocation> () {});

			result = singleResponse.getBody();
			
			
			return result;
		} catch (HttpClientErrorException e) {
			if (e.getStatusCode() == HttpStatus.NOT_FOUND) {
				log.debug(LOCATION_NOT_FOUND, e);
				return null;
			}
			// Rethrow
			throw e;
		} catch (URISyntaxException urie){
			throw new InventarioBatchException("Error getting location by code cannot build url", urie);
		}
	}
	
	public void deleteLocation(ResourceLocation resurceLocation) {
		
		try {
			
			String searchURL = String.join("/", ossInventoryURI, LOCATIONS, 
				resurceLocation.getCountry().getCode(),
				resurceLocation.getProvince().getCode(),
				resurceLocation.getRegion().getCode(),
				resurceLocation.getTown().getCode(),
				resurceLocation.getCode()
				);
			
			URIBuilder builder = new URIBuilder(searchURL);
			builder.addParameter(CHILDREN, "true");
			
			getRestTemplate().delete(
					
					builder.build()
					);

			
		} catch (HttpClientErrorException e) {
			if (e.getStatusCode() == HttpStatus.NOT_FOUND) {
				log.debug(LOCATION_NOT_FOUND, e);
			}
			// Rethrow
			throw e;
		} catch (URISyntaxException urie){
			throw new InventarioBatchException("Error deleting location by code cannot build url", urie);
		}
	}
	
	public ResourceLocation createLocation(ResourceLocation resurceLocation) {
		ResourceLocation result = null;
		
		try {
			
			String searchURL = String.join("/", ossInventoryURI, LOCATIONS, 
				resurceLocation.getCountry().getCode(),
				resurceLocation.getProvince().getCode(),
				resurceLocation.getRegion().getCode(),
				resurceLocation.getTown().getCode()
				);
			
			URIBuilder builder = new URIBuilder(searchURL);
			
			ResourceLocation post = new ResourceLocation();
			post.setAddress(resurceLocation.getAddress());
			post.setCode(resurceLocation.getCode());
			post.setName(resurceLocation.getName());
			post.setLatitude(resurceLocation.getLatitude());
			post.setLongitude(resurceLocation.getLongitude());
			
			result = getRestTemplate().postForObject(
					builder.build(), post, ResourceLocation.class);

			
			return result;
		} catch (HttpClientErrorException e) {
			if (e.getStatusCode() == HttpStatus.NOT_FOUND) {
				log.debug("Cannot create location not found", e);
				return null;
			} else if (e.getStatusCode() == HttpStatus.CONFLICT){
				log.debug("Location already exists ", e);
				return resurceLocation;
			}
			// Rethrow
			throw e;
		} catch (URISyntaxException urie){
			throw new InventarioBatchException("Error creating location cannot build url", urie);
		}
	}
	
	public List<ResourceLocation> getServiceLocations(String serviceId) {
		
		ResponseEntity<List<ResourceLocation>> response = getRestTemplate().exchange(ossInventoryURI + SERVICES+encodeURIComponent(serviceId)+"/location", HttpMethod.GET, null,
				new ParameterizedTypeReference<List<ResourceLocation>>() {});
		
		return response.getBody();
	}
	
	public void addLocationToService(String serviceName, String locationCode) {
		
		
		Map<String, String> uriParameters = new HashMap<>();
		uriParameters.put(SERVICE_NAME, serviceName);
		uriParameters.put(RESOURCE_NAME, locationCode);
	
		try {
		
			ResponseEntity<Void> response = getRestTemplate().exchange(ossInventoryURI + SERVICES+encodeURIComponent(serviceName)+"/location/"+locationCode
			/*encodeURIComponent(locationCode)*/, 
					HttpMethod.POST, 
					null,
					Void.class);
			
			response.getBody();
			
		} catch (HttpClientErrorException e) {
			//Si es 409 entonces el recurso ya esta en un service ...
			//Otros caso son un error...
			switch (e.getStatusCode().value()) {
			case 409:
			case 404:
				break;
			default:
				throw e;
			}
		}
		
	}

	public Service updateService(
			Service service) {
		
		
		Service copy = new Service();
		copy.setIdService(service.getIdService());
		copy.setNameService(service.getNameService());
		copy.setIdCustomer(service.getIdCustomer());
		copy.setNameCustomer(service.getNameCustomer());
		copy.setComment(service.getComment());
		copy.setDescription(service.getDescription());
		
		ResponseEntity<Service> response = getRestTemplate().exchange(ossInventoryURI + SERVICES+encodeURIComponent(copy.getIdService()), 
				HttpMethod.PUT, new HttpEntity<Service>(copy),
				Service.class);
		
		return response.getBody();
	}

	public void removeLocationFromService(String serviceName, String locationName) {
		
		Map<String, String> uriParameters = new HashMap<>();
		uriParameters.put(SERVICE_NAME, serviceName);
		uriParameters.put(RESOURCE_NAME, locationName);
	
		try {
		
			ResponseEntity<Void> response = getRestTemplate().exchange(ossInventoryURI + SERVICES+encodeURIComponent(serviceName)+"/location/"+locationName
			/*encodeURIComponent(locationCode)*/, 
					HttpMethod.DELETE, 
					null,
					Void.class);
			
			response.getBody();
			
		} catch (HttpClientErrorException e) {
			//Si es 409 entonces el recurso ya esta en un service ...
			//Otros caso son un error...
			switch (e.getStatusCode().value()) {
			case 409:
			case 404:
				break;
			default:
				throw e;
			}
		}
	}
	
	public void deleteResource(String resourceName) {
		getRestTemplate().delete(ossInventoryURI + RESOURCES + encodeURIComponent(resourceName));
	}
	
}
