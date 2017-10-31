[![MIT Licence](https://badges.frapsoft.com/os/mit/mit.svg?v=103)](https://opensource.org/licenses/mit-license.php)
[![stable](http://badges.github.io/stability-badges/dist/stable.svg)](http://github.com/badges/stability-badges)
[![Open Source Love](https://badges.frapsoft.com/os/v1/open-source.png?v=103)](https://github.com/ellerbrock/open-source-badge/)

# Ejemplo sencillo de spring boot webservice con SOAP #


Aplicación ***Spring-boot+>spring-boot-starter-web-services***, generada con el wizard ***Spring Started Project*** del [**IDE Spring Tool Suite (STS)**](https://spring.io/tools "IDE Spring Tool Suite")

Producir un servicio web SOAP

Esta guía lo guía a través del proceso de creación de un servidor de servicios web basado en SOAP con Spring.

## Lo que vas a construir ##

Construirá un servidor que expone datos de varios países europeos utilizando un servicio web SOAP basado en WSDL.

Para simplificar el ejemplo, usará datos codificados para el Reino Unido, España y Polonia.

## Página de explicación de su construcción ##

https://spring.io/guides/gs/producing-web-service/

https://github.com/spring-guides/gs-producing-web-service

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


- Configurar beans de servicio web



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


Si está utilizando Maven, puede ejecutar la aplicación usando ./mvnw spring-boot:run. O puede construir el archivo JAR con ./mvnw clean package. Entonces puede ejecutar el archivo JAR:

java -jar target / gs-soap-service-0.1.0.jar
El procedimiento anterior creará un JAR ejecutable. También puede optar por construir un archivo WAR clásico en su lugar.
Se muestra la salida de registro. El servicio debería estar en funcionamiento en unos pocos segundos.

Prueba la aplicación

Ahora que la aplicación se está ejecutando, puede probarlo. Cree un archivo que request.xmlcontenga la siguiente solicitud SOAP:

<soapenv:Envelope xmlns:soapenv="http://schemas.xmlsoap.org/soap/envelope/"
				  xmlns:gs="http://spring.io/guides/gs-producing-web-service">
   <soapenv:Header/>
   <soapenv:Body>
      <gs:getCountryRequest>
         <gs:name>Spain</gs:name>
      </gs:getCountryRequest>
   </soapenv:Body>
</soapenv:Envelope>
Existen algunas opciones cuando se trata de probar la interfaz SOAP. Puede usar algo como SoapUI o simplemente usar herramientas de línea de comandos si está en un sistema * nix / Mac como se muestra a continuación.

$ curl --header "content-type: text/xml" -d @request.xml http://localhost:8080/ws
Como resultado, debería ver esta respuesta:


<?xml version="1.0"?>
<SOAP-ENV:Envelope xmlns:SOAP-ENV="http://schemas.xmlsoap.org/soap/envelope/">
  <SOAP-ENV:Header/>
  <SOAP-ENV:Body>
    <ns2:getCountryResponse xmlns:ns2="http://spring.io/guides/gs-producing-web-service">
      <ns2:country>
        <ns2:name>Spain</ns2:name>
        <ns2:population>46704314</ns2:population>
        <ns2:capital>Madrid</ns2:capital>
        <ns2:currency>EUR</ns2:currency>
      </ns2:country>
    </ns2:getCountryResponse>
  </SOAP-ENV:Body>
</SOAP-ENV:Envelope>




### urls: ###

- ruta del wsdl

http://localhost:8080/ws/countries.wsdl
    
- endpoint

http://localhost:8080/ws