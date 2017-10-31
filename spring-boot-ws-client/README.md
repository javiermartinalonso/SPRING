[![MIT Licence](https://badges.frapsoft.com/os/mit/mit.svg?v=103)](https://opensource.org/licenses/mit-license.php)
[![stable](http://badges.github.io/stability-badges/dist/stable.svg)](http://github.com/badges/stability-badges)
[![Open Source Love](https://badges.frapsoft.com/os/v1/open-source.png?v=103)](https://github.com/ellerbrock/open-source-badge/)

# Ejemplo sencillo de spring boot web-client con SOAP #


Aplicación ***Spring-boot+>spring-boot-starter-web-services***, generada con el wizard ***Spring Started Project*** del [**IDE Spring Tool Suite (STS)**](https://spring.io/tools "IDE Spring Tool Suite")

Esta guía lo guiará por el proceso de consumir un servicio web basado en SOAP con Spring.

Partimos de la guia [https://spring.io/guides/gs/consuming-web-service/](https://spring.io/guides/gs/consuming-web-service/ "https://spring.io/guides/gs/consuming-web-service/").

## Lo que vas a construir ##

Construirá un cliente que obtenga datos meteorológicos de un servicio web remoto basado en WSDL utilizando SOAP . Puede obtener más información sobre el servicio de presupuesto en http://www.webservicex.com/stockquote.asmx?op=GetQuote .

El servicio ofrece cotizaciones bursátiles. Podrás usar tu propio símbolo de cotización.

## Página de explicación de su construcción ##

[https://spring.io/guides/gs/consuming-web-service/](https://spring.io/guides/gs/consuming-web-service/ "https://spring.io/guides/gs/consuming-web-service/").

## Requisitos ##

- **Alrededor de 15 minutos**
- **Un editor de texto favorito o IDE**
- **JDK 1.8 o posterior**
- **Gradle 2.3+ o Maven 3.0+**

## Compilar y pasar los test ##

Con maven desde la carpeta raiz que contiene el .pom del módulo, ejecutamos:

    mvn clean install



## Pasos en la creación del proyecto ##

- Crear esqueleto con el wizard, seleccionando como war. 
 



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

Si tu abres un navegador web en la ruta `localhost:8080` deberías poder ver la siguiente salida en el navegador:

    Hello World!

Para salir correctamente de la aplicación presione `ctrl-c`.


Si está utilizando Maven, puede ejecutar la aplicación usando ./mvnw spring-boot:run. O puede construir el archivo JAR con ./mvnw clean package. Entonces puede ejecutar el archivo JAR:

java -jar target / gs-soap-service-0.1.0.jar
El procedimiento anterior creará un JAR ejecutable. También puede optar por construir un archivo WAR clásico en su lugar.
Se muestra la salida de registro. El servicio debería estar en funcionamiento en unos pocos segundos.

## Prueba la aplicación ##




### urls: ###

