[![MIT Licence](https://badges.frapsoft.com/os/mit/mit.svg?v=103)](https://opensource.org/licenses/mit-license.php)
[![stable](http://badges.github.io/stability-badges/dist/stable.svg)](http://github.com/badges/stability-badges)
[![Open Source Love](https://badges.frapsoft.com/os/v1/open-source.png?v=103)](https://github.com/ellerbrock/open-source-badge/)

# Ejemplo de spring #

https://www.youtube.com/watch?v=j7rQstg94Hk


10 Ways to Get Super Productive with Spring Boot

Aplicación ******, generada con el wizard ***Spring Started Project*** del [**IDE Spring Tool Suite (STS)**](https://spring.io/tools "IDE Spring Tool Suite")


## Lo que vas a construir ##

- devtools
	- cambiar algún texto en una pagina y ver como cambia automáticamente.
- pagina de control de errores
	- 2xx
	- 4xx
- login outh contra github
- inyectar fichero properties por configuracion
- h2 embebido
- ejemplo de flyway (gestion de cambios en bbdd)
- perfiles para conectar con una bbdd u otra dependiendo del perfil.
	- crear un schema de mysql
	- en el aplication.properties indicar los perfiles
	
		`spring.profiles.include=secrets,local`

	o bien a la hora de compilar o ejecutar con spring boot indicar los perfiles.

- frontend tymeleaft

## Página de explicación de su construcción ##



## Requisitos ##

- **Alrededor de 15 minutos**
- **Un editor de texto favorito o IDE**
- **JDK 1.8 o posterior**
- **Maven 3.0+**

## Compilar y pasar los test ##

Con maven desde la carpeta raíz que contiene el .pom del módulo, ejecutamos:

    mvn clean install

## Pasos en la creación del proyecto ##

- Crear esqueleto con el wizard, seleccionando como war y añadimos la dependencia. 

	

### Ejecuntando la aplicación ###

En este punto nuestra aplicación debe funcionar. Puesto que hemos utilizado el POM de ***spring-boot-starter-parent***, tenemos un método run de ejecución que podemos usar para iniciar la aplicación. Escribe `mvn spring-boot:run` desde línea de comandos en el directorio raíz del proyecto para iniciar la aplicación:

O puedes construir el archivo JAR con `./mvnw clean package`. Entonces puedes ejecutar el archivo JAR:


## Referencias ##