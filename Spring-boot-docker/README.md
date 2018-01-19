[![MIT Licence](https://badges.frapsoft.com/os/mit/mit.svg?v=103)](https://opensource.org/licenses/mit-license.php)
[![stable](http://badges.github.io/stability-badges/dist/stable.svg)](http://github.com/badges/stability-badges)
[![Open Source Love](https://badges.frapsoft.com/os/v1/open-source.png?v=103)](https://github.com/ellerbrock/open-source-badge/)

# Ejemplo de spring boot  #



Aplicación ******, generada con el wizard ***Spring Started Project*** del [**IDE Spring Tool Suite (STS)**](https://spring.io/tools "IDE Spring Tool Suite")


## Lo que vas a construir ##



## Página de explicación de su construcción ##

https://juliomunoz.wordpress.com/2015/01/20/introduccion-a-docker/


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