[![MIT Licence](https://badges.frapsoft.com/os/mit/mit.svg?v=103)](https://opensource.org/licenses/mit-license.php)
[![stable](http://badges.github.io/stability-badges/dist/stable.svg)](http://github.com/badges/stability-badges)
[![Open Source Love](https://badges.frapsoft.com/os/v1/open-source.png?v=103)](https://github.com/ellerbrock/open-source-badge/)

# Ejemplo de spring boot con spring-hateoas 

Hypermedia as the Engine of Application State (hipermedia como motor del estado de la aplicación). Es una restricción de la arquitectura de la aplicación REST que lo distingue de la mayoría de otras arquitecturas.#

Aplicación ***Spring-boot+spring-hateoas***, generada con el wizard ***Spring Started Project*** del [**IDE Spring Tool Suite (STS)**](https://spring.io/tools "IDE Spring Tool Suite")


## Lo que vas a construir ##

Construirá una api rest hateoas (Hypermedia as the Engine of Application State).

En este caso exponemos los paises, provincias y municipios.

## Página de explicación de su construcción ##

https://docs.spring.io/spring-boot/docs/current/reference/html/howto-database-initialization.html

https://dzone.com/articles/spring-data-jpa-with-an-embedded-database-and-spring-boot

https://docs.spring.io/spring-boot/docs/current/reference/html/boot-features-sql.html

https://github.com/alexbt/sample-spring-boot-data-jpa-embedded

https://dzone.com/articles/integrate-h2-database-in-your-spring-boot-applicat

http://www.devglan.com/spring-boot/spring-boot-h2-database-example

http://www.javainterviewpoint.com/spring-data-jpa-one-to-many/


http://javasampleapproach.com/spring-framework/spring-data/configure-spring-jpa-one-to-many-relationship-springboot


## base de datos H2 e integración de SpringBoot ##
La base de datos H2 tiene una huella pequeña (más pequeña que 1.5 MB) con requisitos de memoria bajos. Es compatible con múltiples esquemas y SQL estándar, API JDBC. Podemos usar H2 con bases de datos basadas en disco o en memoria .

H2 se puede construir mediante el siguiente modo: 
- Embedded mode o Modo incrustado (conexiones locales) 
- Server mode o Modo de servidor (conexiones remotas) 
- Mixed mode o Modo mixto (conexiones locales y remotas)


## Requisitos ##

- **Alrededor de 15 minutos**
- **Un editor de texto favorito o IDE**
- **JDK 1.8 o posterior**
- **Maven 3.0+**

## Compilar y pasar los test ##

Con maven desde la carpeta raiz que contiene el .pom del módulo, ejecutamos:

    mvn clean install

## Pasos en la creación del proyecto ##

- Crear esqueleto con el wizard, seleccionando como jar y añadimos la dependencia de h2


Dependencias de Maven Para cargar una base de datos integrada con Spring Boot, todo lo que realmente necesita es agregar su dependencia de maven a su pom. El resto será atendido. En mi caso, elegí h2, así que agregué la siguiente dependencia:

	<dependency>
	    <groupId>com.h2database</groupId>
	    <artifactId>h2</artifactId>
	</dependency>


Configuración Spring Boot

	spring.datasource.url=jdbc:h2:mem:TEST;MVCC=true;DB_CLOSE_DELAY=-1;MODE=Oracle
	spring.datasource.username=sa
	spring.datasource.password=
	spring.datasource.driver-class-name=org.h2.Driver
	spring.datasource.platform=h2
	
	spring.datasource.initialize=true
	#datasource.schema=
	#datasource.data
	spring.h2.console.enabled=true
	spring.jpa.hibernate.ddl-auto=none

- **spring.datasource.***: configura una base de datos H2 en memoria;
- **spring.datasource.initialize** : le dice a spring-boot que inicialice la base de datos con scripts;
- **datasource.schema** : el script schema sql para cargar. Por defecto, es el esquema $ {plataforma} .sql luego schema.sql;
- **datasource.data** : el script sql de datos. Por defecto, son datos $ {platform} .sql y luego data.sql;
- **spring.h2.console.enabled** : nos permite acceder a la base de datos de memoria desde una interfaz web;
- **spring.jpa.hibernate.ddl-auto** : hibernates también intenta inicializar la base de datos. Cuando detecta una base de datos incrustada, establece ddl-auto para crear-soltar e inicializar la base de datos con entidades anotadas con @Table (y también busca imports.sql). Esto puede llevar a crear la misma tabla dos veces. Prefiero seguir con la magia de Spring Boot, así que configuré esto para ninguno

> ¡Importante! : 
- jdbc: h2: mem se usa para definir bases de datos en memoria . 
- jdbc: h2: file se utiliza para definir bases de datos basadas en disco .

### Ejecuntando la aplicación ###

En este punto nuestra aplicación debe funcionar. Puesto que hemos utilizado el POM de ***spring-boot-starter-parent***, tenemos un método run de ejecución que podemos usar para iniciar la aplicación. Escribe `mvn spring-boot:run` desde línea de comandos en el directorio raíz del proyecto para iniciar la aplicación:

O puedes construir el archivo JAR con `./mvnw clean package`. Entonces puedes ejecutar el archivo JAR:
