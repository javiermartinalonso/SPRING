package es.satec.angolatelecom.sigo.sim.stock.service.rest;

import java.lang.annotation.Annotation;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.annotation.AnnotationUtils;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = AbstractRestController.API_BASE_URI, produces = { MediaType.APPLICATION_JSON_VALUE })
public class AbstractRestController {
	protected static Logger log = LoggerFactory.getLogger(AbstractRestController.class);
	
	/** base URI para los servicios rest */
	public static final String API_BASE_URI = "/services/v1.0/";

	/** */
	private static final String MSG_INTERNAL_ERROR = "Se produjo un error interno";

	/** */
	protected static final String OPERATION_NOT_IMPLEMENTED = "not implemented";
	
	/**
	 * Handler genérico para errores
	 * 
	 * @param response
	 * @param e
	 * @throws Exception
	 */
	@ExceptionHandler(Exception.class)
	protected ResponseEntity<String> handleError(Exception e) {
		HttpStatus status;
		String reason;

		// si la excepción ya está anotada con @ResponseStatus
		Annotation annotation = AnnotationUtils.findAnnotation(e.getClass(), ResponseStatus.class);
		if (annotation != null) {
			status = (HttpStatus) AnnotationUtils.getValue(annotation, "value");
			reason = e.getMessage();
		} else if (e instanceof IllegalArgumentException) {
			// si es un error en la llamada al servicio
			status = HttpStatus.BAD_REQUEST;
			reason = e.getMessage();
		} else if (e instanceof HttpMessageNotReadableException) {
			// El JSON Recibido no pudo mapearse al objeto esperado: Dejo traza
			// porque NO SUELE SALIR
			String err = e.toString();
			log.error("[@ExceptionHandler] Exc.: {}", err, e);
			status = HttpStatus.BAD_REQUEST;
			reason = e.getMessage();
		} else {
			// si es otro tipo de error (no controlado): Dejo traza también.
			String err = e.toString();
			log.error("[@ExceptionHandler] Exc.: {}", err, e);
			status = HttpStatus.INTERNAL_SERVER_ERROR;
			reason = MSG_INTERNAL_ERROR;
		}

		if (reason == null) {
			reason = MSG_INTERNAL_ERROR;
		}

		// Como "reason" tenga Saltos de línea o Retornos de carro. la hemos
		// liado:
		reason = reason.replaceAll("\n", "").replaceAll("\r", "");
		// Como "reason" lleve '"' la hemos liado. Podría no haber problema pero
		// como no somos adivinos,
		// pues entonces escapamos tanto '"' como '\' como como '\"'.
		reason = reason.replace('"', '\'').replace('\\', ' ');

		String jsonReason = String.format("{ \"errorCode\": %d, \"errorMsg\": \"%s\" }", status.value(), reason);
		return new ResponseEntity<>(jsonReason, status);
	}
}
