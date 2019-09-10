package es.satec.angolatelecom.inventory.dto.entities.annotations;

import static java.lang.annotation.ElementType.TYPE;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

import java.lang.annotation.Retention;
import java.lang.annotation.Target;

@Target(TYPE)
@Retention(RUNTIME)
public @interface ResourceType {

	String name() default "";
	
	/**
	 * It's the orientClass in inventory
	 * @return
	 */
	String inventoryClass();
	
}
