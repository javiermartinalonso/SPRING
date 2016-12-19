[![MIT Licence](https://badges.frapsoft.com/os/mit/mit.svg?v=103)](https://opensource.org/licenses/mit-license.php)
[![stable](http://badges.github.io/stability-badges/dist/stable.svg)](http://github.com/badges/stability-badges)
[![Open Source Love](https://badges.frapsoft.com/os/v1/open-source.png?v=103)](https://github.com/ellerbrock/open-source-badge/)

# Ejemplo sencillo de spring mvc con maven #
Simplemente es un hola mundo por un lado y por otro lado usar MVC para obtener datos

## Requisitos ##

- **Apache Maven 3.3.9**
- **JDK 8**
- **apache-tomcat-6.0.41**

## ¿Cómo ejecutarlo? ##

Desde linea de comandos, nos situamos en el directorio raíz del proyecto y compilar con maven `mvn clean install` para obtener un fichero war en la carpeta **\SpringMaven\target\SpringMaven.war** y a continuación **desplegar en un tomcat**.

### urls: ###

-  Hola mundo!!:

[http://localhost:8080/SpringMaven](http://localhost:8080/SpringMaven)
    
- datos en formato xml

[http://localhost:8080/SpringMaven/student.xml](http://localhost:8080/SpringMaven/student.xml)

[http://localhost:8080/SpringMaven/studentlist.xml](http://localhost:8080/SpringMaven/studentlist.xml)