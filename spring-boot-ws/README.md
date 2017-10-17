[![MIT Licence](https://badges.frapsoft.com/os/mit/mit.svg?v=103)](https://opensource.org/licenses/mit-license.php)
[![stable](http://badges.github.io/stability-badges/dist/stable.svg)](http://github.com/badges/stability-badges)
[![Open Source Love](https://badges.frapsoft.com/os/v1/open-source.png?v=103)](https://github.com/ellerbrock/open-source-badge/)

# Ejemplo sencillo de spring boot webservice con SOAP #


Aplicación ***Spring-boot+>spring-boot-starter-web-services***, generada con el wizard ***Spring Started Project*** del [**IDE Spring Tool Suite (STS)**](https://spring.io/tools "IDE Spring Tool Suite")

Esta aplicación sirve de muestra para probar spring-boot, se puede ejecutar directamente en un servidor Apache Tomcat 7 incorporado, sin necesidad de tenerlo instalado.

## Página de explicación de su construcción ##



## Requisitos ##

- **Apache Maven 3.3.9**
- **JDK 8**

## Compilar y pasar los test ##

Con maven desde la carpeta raiz que contiene el .pom del módulo, ejecutamos:

    mvn clean install



## Pasos en la creación del proyecto ##

- Crear esqueleto con el wizard, seleccionando como jar y modulo webservices 
 
- Crear un esquema XML para definir el dominio.
	- 	Cree un archivo XSD con operaciones para devolver el nombre , la población , el capital y la moneda de un país :


- Generar clases de dominio basadas en un esquema XML
	- Configuración de plugin para maven:

- Crear repositorio de país

- Crear end-point del servicio de país






### Ejecuntando la aplicación ###

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


### urls: ###

-  Hola mundo!!:

[http://localhost:8080/SpringMaven](http://localhost:8080/SpringMaven)
    
- datos en formato xml

[http://localhost:8080/SpringMaven/student.xml](http://localhost:8080/SpringMaven/student.xml)

[http://localhost:8080/SpringMaven/studentlist.xml](http://localhost:8080/SpringMaven/studentlist.xml)