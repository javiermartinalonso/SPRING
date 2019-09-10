package es.satec.angolatelecom.inventory.rest.client;

import org.apache.http.client.config.RequestConfig;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.client.ClientHttpRequestFactory;
import org.springframework.http.client.HttpComponentsClientHttpRequestFactory;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

@Component
public class InventoryRestClient implements InitializingBean {

	private static final Logger log = LoggerFactory.getLogger(InventoryRestClient.class);
	
	private RestTemplate restTemplate = new RestTemplate(getClientHttpRequestFactory());

	@Value("${inventory.oss.endpoint.uri}")
	private String inventoryBaseUri;
	
	@Override
	public void afterPropertiesSet() throws Exception {
		restTemplate = new RestTemplate(getClientHttpRequestFactory());
	}

	public RestTemplate getRestTemplate() {
		return restTemplate;
	}

	public String getServiceUri() {
		return inventoryBaseUri;
	}

	private ClientHttpRequestFactory getClientHttpRequestFactory() {
		int timeout = 600000;
		RequestConfig config = RequestConfig.custom().setConnectTimeout(timeout).setConnectionRequestTimeout(timeout)
				.setSocketTimeout(timeout).build();
		CloseableHttpClient client = HttpClientBuilder.create().setDefaultRequestConfig(config).build();
		return new HttpComponentsClientHttpRequestFactory(client);
	}
	
	/**
	public HSSAucResponse altaHSSAuc(HSSAuc hssAuc) {
		log.debug("** altaHSSAuc ** {}", hssAuc);

		try {
			ResponseEntity<HSSAucResponse> response = getRestTemplate()
					.postForEntity(getServiceUri() + "/simHSSAuc", hssAuc, HSSAucResponse.class);

			return response.getBody();
		} catch (RestClientException e) {
			log.debug("Error altaHSSAuc {}", hssAuc, e);
			HSSAucResponse hssAucResponse = new HSSAucResponse(hssAuc);
			hssAucResponse.setResponseOK(false);
			return hssAucResponse;
		}
	}	
	*/
	
	
}
