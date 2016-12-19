

[![MIT Licence](https://badges.frapsoft.com/os/mit/mit.svg?v=103)](https://opensource.org/licenses/mit-license.php)
[![stable](http://badges.github.io/stability-badges/dist/stable.svg)](http://github.com/badges/stability-badges)
[![Open Source Love](https://badges.frapsoft.com/os/v1/open-source.png?v=103)](https://github.com/ellerbrock/open-source-badge/)

# Ejemplo sencillo de Spring-DATA + hibernate + JPA + postgreSQL #

Módulo java que usa **Spring-DATA + hibernate + JPA** contra una bbdd de tipo **postgreSQL**
ademas tiene creado su conjunto de **test contra un bbdd h2 embebido** 

## Requisitos ##

- **Apache Maven 3.3.9**
- **JDK 8**
- **apache-tomcat-6.0.41**
- **servidor postgreSQL** y ***creadas las tablas necesarias para el proyecto***.

**PENDIENTE DE INCLUIR LOS SCRIPTS DE CREACIÓN DE LA BBDD QUE USA EL PROYECTO.**

## Compilar y pasar los test ##

Con maven desde la carpeta raíz que contiene el .pom del módulo, ejecutamos:

    mvn clean install

## ¿Cómo ejecutarlo? ##

Desde eclipse ejecutar ***run as java aplication*** la clase principal main:

***es.jmartin.ejemplos.spring.data.controlador.PruebaFuncionamiento.java***
