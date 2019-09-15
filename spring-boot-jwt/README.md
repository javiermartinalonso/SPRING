[![MIT Licence](https://badges.frapsoft.com/os/mit/mit.svg?v=103)](https://opensource.org/licenses/mit-license.php)
[![stable](http://badges.github.io/stability-badges/dist/stable.svg)](http://github.com/badges/stability-badges)
[![Open Source Love](https://badges.frapsoft.com/os/v1/open-source.png?v=103)](https://github.com/ellerbrock/open-source-badge/)

# Ejemplo sencillo de spring boot rest con seguridad JWT #


Aplicación ***Spring-boot + spring-boot-starter-web + spring-boot-starter-security + JWT***, generada con el wizard ***Spring Started Project*** del [**IDE Spring Tool Suite (STS)**](https://spring.io/tools "IDE Spring Tool Suite")

## Lo que vas a construir ##

Vamos a producir y consumir un servicio rest protegido por JWT

En esta guia estudiaremos como crear un servidor de autenticación y un cliente basado en el estandar JWT con Spring security.

## Página de explicación de su construcción ##



## Otras referencias ##



## Requisitos ##

- **Apache Maven 3.3.9**
- **JDK 8**

## Compilar y pasar los test ##

Con maven desde la carpeta raiz que contiene el .pom del módulo, ejecutamos:

    mvn clean install



## Pasos en la creación del proyecto ##

- Crear esqueleto con el wizard, seleccionando como jar y modulo webservices - 
- Crear un API REST con Spring Boot.
- Proteger recursos publicados en el API.
- Implementar un controlador para autenticar usuarios y generar un token de acceso.
- Implementar un filtro para autorizar peticiones a recursos protegidos de nuestro API.



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


Si está utilizando Maven, puede ejecutar la aplicación usando ```./mvnw spring-boot:run```. O puede construir el archivo JAR con ```./mvnw clean package```. Entonces puede ejecutar el archivo JAR:

```java -jar target / gs-soap-service-0.1.0.jar```

El procedimiento anterior creará un JAR ejecutable. También puede optar por construir un archivo WAR clásico en su lugar.
Se muestra la salida de registro. El servicio debería estar en funcionamiento en unos pocos segundos.

### Prueba la aplicación ###

Ahora que la aplicación se está ejecutando, puede probarlo. Cree una petición rest


Existen algunas opciones cuando se trata de probar la interfaz REST. Puede usar clientes como postman o insomnia o simplemente usar herramientas de línea de comandos si está en un sistema unix como se muestra a continuación.


#Se lanza una petición de logi n#
```$ curl -i -H "Content-Type: application/json" -X POST -d '{ "username": "admin", "password": "password"}' http://localhost:8080/login```
	
# Recuperamos los usuarios dados de alta #
```$ curl -H "Authorization: Bearer xxx.yyy.zzz" http://localhost:8080/users/```
	
# Damos de alta un nuevo usuario #
```$ curl -i -H 'Content-Type: application/json' -H 'Authorization: Bearer xxx.yyy.zzz' -X POST -d '{ "```
	
Como resultado, debería ver esta respuesta:




### urls: ###

- ruta del servicio

http://localhost:8080/hello
    
- endpoint autentication

http://localhost:8080/authentication