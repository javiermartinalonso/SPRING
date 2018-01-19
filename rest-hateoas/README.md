[![MIT Licence](https://badges.frapsoft.com/os/mit/mit.svg?v=103)](https://opensource.org/licenses/mit-license.php)
[![stable](http://badges.github.io/stability-badges/dist/stable.svg)](http://github.com/badges/stability-badges)
[![Open Source Love](https://badges.frapsoft.com/os/v1/open-source.png?v=103)](https://github.com/ellerbrock/open-source-badge/)

# Ejemplo de spring boot con spring-hateoas #

Hypermedia as the Engine of Application State (hipermedia como motor del estado de la aplicación). Es una restricción de la arquitectura de la aplicación REST que lo distingue de la mayoría de otras arquitecturas.

Aplicación ***Spring-boot+spring-hateoas***, generada con el wizard ***Spring Started Project*** del [**IDE Spring Tool Suite (STS)**](https://spring.io/tools "IDE Spring Tool Suite")


## Lo que vas a construir ##

Esta guía lo guía a través del proceso de creación de una aplicación que accede a los datos de JPA relacionales a través de una interfaz RESTful basada en hipermedios .

Construirá una aplicación Spring que le permitirá crear y recuperar Personobjetos almacenados en una base de datos utilizando Spring Data REST. Spring Data REST toma las características de Spring HATEOAS y Spring Data JPA y las combina automáticamente.


## Página de explicación de su construcción ##
https://geeks-mexico.com/2018/01/04/crea-un-api-rest-de-la-forma-mas-simple-con-java-y-spring-data-rest/



https://spring.io/guides/gs/accessing-data-rest/









https://marceloagustini.wordpress.com/2013/09/15/json-problema-de-serializacion-bi-direccional/







https://www.adictosaltrabajo.com/tutoriales/spring-hateoas/




https://springframework.guru/spring-boot-restful-api-documentation-with-swagger-2/


http://www.robertocrespo.net/kaizen/implementar-microservicios-spring-boot-iv-documentar-apis-rest-swagger/


https://dzone.com/articles/spring-boot-restful-api-documentation-with-swagger


http://desarrollo-java.readthedocs.io/es/latest/tutorial4.md.html

## Requisitos ##

- **Alrededor de 15 minutos**
- **Un editor de texto favorito o IDE**
- **JDK 1.8 o posterior**
- **Maven 3.0+**

## Compilar y pasar los test ##

Con maven desde la carpeta raiz que contiene el .pom del módulo, ejecutamos:

    mvn clean install

## Pasos en la creación del proyecto ##

- Crear esqueleto con el wizard, seleccionando como war y añadimos la dependencia de:

		<!-- hipermedia restfull -->
		<dependency>
			<groupId>org.springframework.boot</groupId>
			<artifactId>spring-boot-starter-data-rest</artifactId>
		</dependency>

 

	

### Ejecuntando la aplicación ###

En este punto nuestra aplicación debe funcionar. Puesto que hemos utilizado el POM de ***spring-boot-starter-parent***, tenemos un método run de ejecución que podemos usar para iniciar la aplicación. Escribe `mvn spring-boot:run` desde línea de comandos en el directorio raíz del proyecto para iniciar la aplicación:

O puedes construir el archivo JAR con `./mvnw clean package`. Entonces puedes ejecutar el archivo JAR:


http://localhost:8080/h2
http://localhost:8080/pais/findall
http://localhost:8080/pais/provincia/2
http://localhost:8080/pais/73/provincias
http://localhost:8080/pais/73


plugging JSON Formatter






[https://spring.io/guides/gs/accessing-data-rest/](https://spring.io/guides/gs/accessing-data-rest/ "https://spring.io/guides/gs/accessing-data-rest/")


[https://spring.io/guides/gs/rest-hateoas/](https://spring.io/guides/gs/rest-hateoas/ "https://spring.io/guides/gs/rest-hateoas/")

[https://spring.io/understanding/REST](https://spring.io/understanding/REST "https://spring.io/understanding/REST")

http://www.baeldung.com/spring-hateoas-tutorial


http://www.baeldung.com/spring-data-rest-relationships

http://ledze.mx/index.php/10-spring-boot/restful/7-spring-boot-servicio-data-restful-con-acceso-a-datos-con-jpa-front-end-basado-en-restful-hypermedia


https://danielme.com/2014/02/08/persistencia-en-bd-con-spring-data-jpa/

