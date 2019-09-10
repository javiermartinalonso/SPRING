package es.satec.angolatelecom.inventory.service.mapper;

import java.beans.PropertyDescriptor;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.apache.commons.beanutils.PropertyUtils;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.context.annotation.ClassPathScanningCandidateComponentProvider;
import org.springframework.core.annotation.AnnotationUtils;
import org.springframework.core.type.filter.AnnotationTypeFilter;
import org.springframework.stereotype.Component;
import org.springframework.util.Assert;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.util.ClassUtil;

import es.satec.angolatelecom.inventory.domain.EstadoInventario;
import es.satec.angolatelecom.inventory.domain.entities.Resource;
import es.satec.angolatelecom.inventory.dto.entities.InventoryResource;
import es.satec.angolatelecom.inventory.dto.entities.annotations.Id;
import es.satec.angolatelecom.inventory.dto.entities.annotations.ResourceScan;
import es.satec.angolatelecom.inventory.dto.entities.annotations.ResourceType;
import es.satec.angolatelecom.inventory.dto.entities.common.NumeroDeTelefone;
import es.satec.angolatelecom.inventory.exception.InventarioBatchException;

@Component
//@ComponentScan(basePackages = {"es.satec.angolatelecom.inventory.dto.entities"})
@ResourceScan(basePackageClasses = NumeroDeTelefone.class)
public class InventoryResourceMapper implements InitializingBean {
	
	private static final Logger log = LoggerFactory.getLogger(InventoryResourceMapper.class);
	
	private Map<String, InventoryResourceMetadata> metadataMap = new HashMap<>();
	
	private ObjectMapper mapper = new ObjectMapper();
	
	@Override
	public void afterPropertiesSet() throws Exception {
		// Scanear los paquetes para sacar un Map<Class, ResourceMapper> mapa
		ResourceScan resourceScan = this.getClass().getAnnotation(ResourceScan.class);
		
		Assert.notNull(resourceScan);
		Assert.notNull(resourceScan.basePackageClasses()[0]);
		
		String basePackageName = resourceScan.basePackageClasses()[0].getPackage().getName();
		
		
		
		/*
		ComponentScan componentScan = this.getClass().getAnnotation(ComponentScan.class);
		
		Assert.notNull(componentScan);
		Assert.notNull(componentScan.basePackageClasses()[0]);
		
		String basePackageName = componentScan.basePackages()[0].getPackage().getName();
		*/
		
		
		// Resource mapper le das un Recuso de inventario y te da un Recurso...
		ClassPathScanningCandidateComponentProvider scanner = new ClassPathScanningCandidateComponentProvider(false);

		scanner.addIncludeFilter(new AnnotationTypeFilter(ResourceType.class));

		for (BeanDefinition bd : scanner.findCandidateComponents(basePackageName)) {
			log.debug("Registering {}", bd.getBeanClassName());
			
			ResourceType resourceType = AnnotationUtils.findAnnotation(findClass(bd.getBeanClassName()), ResourceType.class);
			
			String reourceName = StringUtils.isNotEmpty(resourceType.name()) ?  resourceType.name() : bd.getBeanClassName();
			
			AnnotationUtils.findAnnotation(findClass(bd.getBeanClassName()), Id.class);
			
			Optional<Field> field = Arrays.asList(findClass(bd.getBeanClassName()).getDeclaredFields()).stream().filter(f-> f.isAnnotationPresent(Id.class)).findFirst();
			
			
			String strTypeClass = resourceType.inventoryClass();
			log.debug("The inventory class is {}",  strTypeClass);
			metadataMap.put(resourceType.inventoryClass(), new InventoryResourceMetadata(bd, resourceType.inventoryClass(), reourceName, field.orElse(null)));
			
		}
	}
	
	public <T extends InventoryResource> Resource convertToResource(T inventoryResource) {		
		List<String> reservedProperties = Arrays.asList("orientClass", "name", "class", "resourceType", "inventoryClass","estadoInventario");
		Resource resource = new Resource(); 
		HashMap<String, Object> properties = new HashMap<>();
		resource.setProperties(properties);
		
		//Intentamos usar la orientClass de la anotacion de forma preferente
		if(inventoryResource.getClass().getAnnotation(ResourceType.class) != null) {
			resource.setOrientClass(inventoryResource.getClass().getAnnotation(ResourceType.class).inventoryClass());
		} else {
			resource.setOrientClass(inventoryResource.getOrientClass());
		}
		
		PropertyDescriptor [] descriptors = PropertyUtils.getPropertyDescriptors(inventoryResource);
		for (PropertyDescriptor descriptor: descriptors) {
			String propertyName = descriptor.getName();
			if (!reservedProperties.contains(propertyName)) {
				Object value;
				try {
					value = PropertyUtils.getProperty(inventoryResource, propertyName);
					if (value!=null)
					{
						if (value.getClass().equals(Date.class))
						{							
							SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
									
							properties.put(propertyName, formatter.format(value));
						}
						else
						{
							properties.put(propertyName, value);
						}
						
					}
				} catch (IllegalAccessException | InvocationTargetException | NoSuchMethodException e) {
					log.error("convertToResource propertyName: ", propertyName, e);
					throw new InventarioBatchException("convertToResource propertyName: " + propertyName, e);
				}
			}
		}
		return resource;
	}
	
	/**
	 * 
	 * @param from
	 * @param toValueType
	 * @return
	 * @throws IllegalArgumentException Si la conversion falla por no encrontrarse el tipo de recurso...
	 */
	@SuppressWarnings("unchecked")
	public <T> T convertValue(Resource from, Class<T> toValueType) {
		
		if(from == null) {
			return null;
		}
		
		InventoryResourceMetadata metadata  = metadataMap.get(from.getOrientClass());
		if(metadata== null) {
			
			log.warn("Cannot infer elements of orientClass " + from.getOrientClass() + " no metadata found\n. Have annotated the class properly with @ResourceType?");
			
			InventoryResource result = mapper.convertValue(from.getProperties(),  InventoryResource.class);
			result.setOrientClass(from.getOrientClass());
			result.setEstadoInventario(EstadoInventario.valueOf(from.getEstadoInventario().name()));
			return (T) result;
			
		} else {
			try {
				Class<?> myClass = findClass(metadata.getBeanDefinition().getBeanClassName());
				// Al instanciar no se rellena el OrientClass y tampoco viene en las from properties
				T convertedValue = (T) mapper.convertValue(from.getProperties(), myClass);
				if (convertedValue!=null && convertedValue instanceof InventoryResource) {
					((InventoryResource)convertedValue).setOrientClass(from.getOrientClass());
					((InventoryResource)convertedValue).setEstadoInventario(EstadoInventario.valueOf(from.getEstadoInventario().name()));
				}
				return convertedValue;
				
			}catch(ClassNotFoundException cnf) {
				throw new IllegalArgumentException("Cannot convert elements of orientClass "+ from.getOrientClass(), cnf);
			}
		}
		
		
	}
	
	/****FORZADO A COPIAR EL CODIGO DE JACKSON 2.6 por conflicto con libreria de wildfly****/
	
	public Class<?> findClass(String className) throws ClassNotFoundException
    {
        if (className.indexOf('.') < 0) {
            Class<?> prim = findPrimitive(className);
            if (prim != null) {
                return prim;
            }
        }
        // Two-phase lookup: first using context ClassLoader; then default
        Throwable prob = null;
        ClassLoader loader = Thread.currentThread().getContextClassLoader();
        
        if (loader != null) {
            try {
                return classForName(className, loader);
            } catch (Exception e) {
                prob = ClassUtil.getRootCause(e);
            }
        }
        try {
            return classForName(className);
        } catch (Exception e) {
            if (prob == null) {
                prob = ClassUtil.getRootCause(e);
            }
        }
        if (prob instanceof RuntimeException) {
            throw (RuntimeException) prob;
        }
        throw new ClassNotFoundException(prob.getMessage(), prob);
    }
    
    protected Class<?> classForName(String name, ClassLoader loader) throws ClassNotFoundException {
    	return Class.forName(name, true, loader);
    }
    
    protected Class<?> classForName(String name) throws ClassNotFoundException {
    	return Class.forName(name);
    }

    protected Class<?> findPrimitive(String className)
    {
        if ("int".equals(className)) return Integer.TYPE;
        if ("long".equals(className)) return Long.TYPE;
        if ("float".equals(className)) return Float.TYPE;
        if ("double".equals(className)) return Double.TYPE;
        if ("boolean".equals(className)) return Boolean.TYPE;
        if ("byte".equals(className)) return Byte.TYPE;
        if ("char".equals(className)) return Character.TYPE;
        if ("short".equals(className)) return Short.TYPE;
        if ("void".equals(className)) return Void.TYPE;
        return null;
    }
    
    /****END****/

	class InventoryResourceMetadata {

		private String orientClass;
		private String resourceType;
		private Field idField;
		private BeanDefinition beanDefinition;

		public InventoryResourceMetadata() {
		}

		public InventoryResourceMetadata(BeanDefinition beanDefinition, String orientClass, String resourceType,
				Field idField) {
			super();
			this.orientClass = orientClass;
			this.resourceType = resourceType;
			this.idField = idField;
			this.beanDefinition = beanDefinition;
		}

		public String getOrientClass() {
			return orientClass;
		}

		public void setOrientClass(String orientClass) {
			this.orientClass = orientClass;
		}

		public String getResourceType() {
			return resourceType;
		}

		public void setResourceType(String resourceType) {
			this.resourceType = resourceType;
		}

		public Field getIdField() {
			return idField;
		}

		public void setIdField(Field idField) {
			this.idField = idField;
		}

		public BeanDefinition getBeanDefinition() {
			return beanDefinition;
		}

		public void setBeanDefinition(BeanDefinition beanDefinition) {
			this.beanDefinition = beanDefinition;
		}

	}

}
