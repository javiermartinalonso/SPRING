[![MIT Licence](https://badges.frapsoft.com/os/mit/mit.svg?v=103)](https://opensource.org/licenses/mit-license.php)
[![stable](http://badges.github.io/stability-badges/dist/stable.svg)](http://github.com/badges/stability-badges)
[![Open Source Love](https://badges.frapsoft.com/os/v1/open-source.png?v=103)](https://github.com/ellerbrock/open-source-badge/)

# Ejemplo de spring boot con spring-data-jpa 

Implementar una capa de acceso a datos de una aplicación ha sido engorroso durante bastante tiempo. Demasiado código repetitivo debe escribirse para ejecutar consultas simples, así como para realizar paginación y auditoría. Spring Data JPA tiene como objetivo mejorar significativamente la implementación de capas de acceso a datos al reducir el esfuerzo a la cantidad que realmente se necesita. Como desarrollador, escribe las interfaces de repositorio, incluidos los métodos de búsqueda personalizados, y Spring proporcionará la implementación de forma automática.


Aplicación ***Spring-boot+spring-data-jpa***, generada con el wizard ***Spring Started Project*** del [**IDE Spring Tool Suite (STS)**](https://spring.io/tools "IDE Spring Tool Suite")


## Lo que vas a construir ##

Construiremos la capa del dominio de objetos de negocio.En este caso los objetos y sus relaciones países, provincias y municipios.

En este módulo sólo usamos las dependencias de javax.persistence incluidas en el modulo de spring-data-jpa, con la intención de definir un modelo de datos que podamos incluir como dependencia en otros módulos y de este modo reutilizarlo en los ejemplos.

Estas dependencias javax.persistence.* forman parte del paquete JPA

JPA (API de Persistencia en Java) es el estándar en Java que define una abstracción que nos permite realizar la integración entre el sistema orientado a objetos de Java y el sistema relacional de nuestra base de datos. Para más detalles consultar la especificación JPA – JSR 338

JPA realiza por nosotros toda la conversión entre nuestros objetos y las tablas de una base de datos. Esta conversión se llama ORM (Mapeo Relacional de Objetos) y puede configurarse a través de metadatos (XML) o anotaciones.

JPA establece un interface común que es implementada por un “JPA Provider” concreto. De modo que es el Proveedor JPA el que realiza el trabajo. Entre los proveedores JPA más conocidos se encuentran Hibernate, Eclipse Link, TopLink, OpenJPA

## Página de explicación de su construcción ##

http://javaespanol.blogspot.com.es/2015/10/jpa-hibernate.html

## Requisitos ##

- **Alrededor de 5 minutos**
- **Un editor de texto favorito o IDE**
- **JDK 1.8 o posterior**
- **Maven 3.0+**

## Compilar y pasar los test ##

Con maven desde la carpeta raiz que contiene el .pom del módulo, ejecutamos:

    mvn clean install

## Pasos en la creación del proyecto ##

- Crear esqueleto con el wizard, seleccionando como jar y añadimos la dependencia de spring-data-jpa. 

	

### Ejecuntando la aplicación ###

Este módulo no es un ejecutable en si mismo, pues sólo hemos definido objetos de dominio.

Puedes construir el archivo JAR con `./mvnw clean package`. Entonces puedes importar dicho jar desde otro módulo de java.
