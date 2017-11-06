[![MIT Licence](https://badges.frapsoft.com/os/mit/mit.svg?v=103)](https://opensource.org/licenses/mit-license.php)
[![stable](http://badges.github.io/stability-badges/dist/stable.svg)](http://github.com/badges/stability-badges)
[![Open Source Love](https://badges.frapsoft.com/os/v1/open-source.png?v=103)](https://github.com/ellerbrock/open-source-badge/)

# Ejemplo spring boot Lombock  Jersey (Jax-RS) JPA H2 Actuator #

https://spring.io/blog/2017/10/18/spring-tips-bootiful-jax-rs

Aplicación ***Lombock  Jersey (Jax-RS) JPA H2 Actuator***, generada con el wizard ***Spring Started Project*** del [**IDE Spring Tool Suite (STS)**](https://spring.io/tools "IDE Spring Tool Suite")

Producir un servicio rest con Jersey

* Lombock
https://projectlombok.org/
Project Lombok es una biblioteca de Java que se conecta automáticamente a su editor y crea herramientas, condimentando su java. Nunca escriba otro getter o setter de nuevo.

Lo que vas a construir

Construirá un servidor que expone servicios rest de clientes, usando los métodos más modernos de Java 8, spring boot...

## Página de explicación de su construcción ##

https://www.youtube.com/watch?v=deB70lfUJBU&index=1&list=PLkQrLrDQK4Z1q7dCOrUYHu7nJMl8JCy5W

## Requisitos ##

- **Apache Maven 3.3.9**
- **JDK 8**

## Compilar y pasar los test ##

Con maven desde la carpeta raiz que contiene el .pom del módulo, ejecutamos:

    mvn clean install



## Pasos en la creación del proyecto ##

- Crear esqueleto con el wizard, seleccionando como jar y módulos Lombock  Jersey (Jax-RS) JPA H2 Actuator.


## Ejecuntando la aplicación ##

En este punto nuestra aplicación debe funcionar. Puesto que hemos utilizado el POM de ***spring-boot-starter-parent***, tenemos un método run de ejecución que podemos usar para iniciar la aplicación. Escribe `mvn spring-boot:run` desde línea de comandos en el directorio raíz del proyecto para iniciar la aplicación:

    $ mvn spring-boot:run
    
      .   ____          _            __ _ _
     /\\ / ___'_ __ _ _(_)_ __  __ _ \ \ \ \
    ( ( )\___ | '_ | '_| | '_ \/ _` | \ \ \ \
     \\/  ___)| |_)| | | | | || (_| |  ) ) ) )
      '  |____| .__|_| |_|_| |_\__, | / / / /
     =========|_|==============|___/=/_/_/_/
     :: Spring Boot ::  (v2.0.0.BUILD-SNAPSHOT)
    ....... . . .
    ....... . . . (log output here)
    ....... . . .
    ........ Started Example in 2.222 seconds (JVM running for 6.514)

Si tu abres un navegador web en la ruta `localhost:8080` deberias poder ver la siguiente salida en el navegador:

    Hello World!

Para salir correctamente de la aplicación presione `ctrl-c`.


Si está utilizando Maven, puede ejecutar la aplicación usando ./mvnw spring-boot:run. O puede construir el archivo JAR con ./mvnw clean package. Entonces puede ejecutar el archivo JAR:

java -jar target / customer-service-0.1.0.jar
El procedimiento anterior creará un JAR ejecutable. También puede optar por construir un archivo WAR clásico en su lugar.
Se muestra la salida de registro. El servicio debería estar en funcionamiento en unos pocos segundos.

## Prueba la aplicación ##



### urls: ###

- endPoint customers

	http://localhost:8080/customers

- endPoint findById

	http://localhost:8080/customers/{id}


- informacion sobre la aplicacion

	http://localhost:8080/application/health

- informacion sobre el entorno o ambiente

	http://localhost:8080/application/env

- lista de los endpoints de la aplicacion

	http://localhost:8080/application
