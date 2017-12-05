[![MIT Licence](https://badges.frapsoft.com/os/mit/mit.svg?v=103)](https://opensource.org/licenses/mit-license.php)
[![stable](http://badges.github.io/stability-badges/dist/stable.svg)](http://github.com/badges/stability-badges)
[![Open Source Love](https://badges.frapsoft.com/os/v1/open-source.png?v=103)](https://github.com/ellerbrock/open-source-badge/)

# Ejemplo de spring boot con spring-cloud-bootstrap #

Spring Cloud Function es un proyecto con los siguientes objetivos de alto nivel:

Promover la implementación de la lógica empresarial a través de funciones.

Desacople el ciclo de vida de desarrollo de la lógica empresarial de cualquier objetivo de tiempo de ejecución específico para que el mismo código pueda ejecutarse como un punto final web, un procesador de flujo o una tarea.

Respalde un modelo de programación uniforme entre los proveedores sin servidor, así como la capacidad de ejecutar de forma autónoma (localmente o en un PaaS).

Habilite las funciones Spring Boot (autoconfiguración, inyección de dependencia, métricas) en proveedores sin servidor.

Se abstrae de todos los detalles del transporte y la infraestructura, lo que permite al desarrollador mantener todas las herramientas y procesos familiares, y enfocarse firmemente en la lógica empresarial.


Es solo una aplicación Spring Boot, por lo que se puede construir, ejecutar y probar, localmente y en una compilación CI, de la misma forma que cualquier otra aplicación Spring Boot. El Functiones de java.utily Fluxes un flujo reactivo Publisher de Project Reactor . Se puede acceder a la función a través de HTTP o mensajes.

Spring Cloud Function tiene 4 características principales:

Contenedores para @Beansel tipo Function, Consumery Supplier, exponiéndolos al mundo exterior, ya sea como extremos HTTP y / o mensaje de corriente de oyentes / editores con RabbitMQ, Kafka etc.

La compilación de cadenas que son cuerpos de funciones de Java en bytecode, y luego convertirlos en @Beanseso se puede envolver como se indicó anteriormente.

Desplegar un archivo JAR que contenga dicho contexto de aplicación con un cargador de clases aislado, para que pueda empaquetarlos juntos en una sola JVM.

Adaptadores para AWS Lambda , Apache OpenWhisk y posiblemente otros proveedores de servicios "sin servidor".


Aplicación ***Spring-boot+spring-cloud-bootstrap***, generada con el wizard ***Spring Started Project*** del [**IDE Spring Tool Suite (STS)**](https://spring.io/tools "IDE Spring Tool Suite")


## Lo que vas a construir ##

un ejemplo básico de spring Cloud Function Web

## Página de explicación de su construcción ##

[https://github.com/markfisher/spring-cloud-function](https://github.com/markfisher/spring-cloud-function "https://github.com/markfisher/spring-cloud-function")

[https://www.youtube.com/watch?v=I2Yu3YoC-mw&feature=youtu.be](https://www.youtube.com/watch?v=I2Yu3YoC-mw&feature=youtu.be "https://www.youtube.com/watch?v=I2Yu3YoC-mw&feature=youtu.be")

## Requisitos ##

- **Alrededor de 15 minutos**
- **Un editor de texto favorito o IDE**
- **JDK 1.8 o posterior**
- **Maven 3.0+**

## Compilar y pasar los test ##

Con maven desde la carpeta raíz que contiene el .pom del módulo, ejecutamos:

    mvn clean install

## Pasos en la creación del proyecto ##

- Crear esqueleto con el wizard, seleccionando como war y añadimos la dependencia de cloud Bootstrap. 

- cambiar la dependencia anterior por esta:

		<dependency>
			<groupId>org.springframework.cloud</groupId>
			<artifactId>spring-cloud-function-web</artifactId>
			<version>1.0.0.BUILD-SNAPSHOT</version>
		</dependency>
		<!-- 
		<dependency>
			<groupId>org.springframework.cloud</groupId>
			<artifactId>spring-cloud-starter</artifactId>
		</dependency>
		-->	

### Ejecuntando la aplicación ###

En este punto nuestra aplicación debe funcionar. Puesto que hemos utilizado el POM de ***spring-boot-starter-parent***, tenemos un método run de ejecución que podemos usar para iniciar la aplicación. Escribe `mvn spring-boot:run` desde línea de comandos en el directorio raíz del proyecto para iniciar la aplicación:

O puedes construir el archivo JAR con `./mvnw clean package`. Entonces puedes ejecutar el archivo JAR:





## cURL Commands

Tu puedes intentar consumir la funcion como servicio ejecutando:


    $ curl localhost:8080/uppercase -H "Content-Type: text/plain" -d 'hello world'

Obtendrás la siguiente respuesta:

    % Total% Received % Xferd  Average Speed   TimeTime Time  Current
    Dload  Upload   Total   SpentLeft  Speed
    10026015  10011 15 11  0:00:01 --:--:--  0:00:01 26000["HELLO WORLD"]


o desde el propio navegador

[http://localhost:8080/uppercase/hola mundo](http://localhost:8080/uppercase/hola%20mundo "http://localhost:8080/uppercase/hola mundo")



## Referencias ##

[http://projects.spring.io/spring-cloud/](http://projects.spring.io/spring-cloud/ "http://projects.spring.io/spring-cloud/")

[https://github.com/markfisher/spring-cloud-function](https://github.com/markfisher/spring-cloud-function "https://github.com/markfisher/spring-cloud-function")


[https://github.com/projectriff/riff](https://github.com/projectriff/riff "https://github.com/projectriff/riff")


[https://github.com/cncf/wg-serverless](https://github.com/cncf/wg-serverless "https://github.com/cncf/wg-serverless")

[https://github.com/dsyer/spring-boot-thin-launcher](https://github.com/dsyer/spring-boot-thin-launcher "https://github.com/dsyer/spring-boot-thin-launcher")

[http://projectreactor.io/](http://projectreactor.io/ "http://projectreactor.io/")