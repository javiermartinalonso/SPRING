[![MIT Licence](https://badges.frapsoft.com/os/mit/mit.svg?v=103)](https://opensource.org/licenses/mit-license.php)
[![stable](http://badges.github.io/stability-badges/dist/stable.svg)](http://github.com/badges/stability-badges)
[![Open Source Love](https://badges.frapsoft.com/os/v1/open-source.png?v=103)](https://github.com/ellerbrock/open-source-badge/)

# Ejemplo sencillo de swing+spring-data+hibernate+jpa con maven #

Simplemente es una aplicación de escritorio realizada con swing, que carga 2 combos: uno con la provincia seleccionada y otro con los municipios de dicha provincia.

## Requisitos ##

- **Apache Maven 3.3.9**
- **JDK 8**

## Compilar y pasar los test ##

Con maven desde la carpeta raiz que contiene el .pom del módulo, ejecutamos:

    mvn clean install

## ¿Cómo ejecutarlo? ##

Desde eclipse ejecutar ***run as java aplication*** la clase principal ***inicio.java***:

***es.jmartin.ejemplos.spring.controlador.Inicio.java***