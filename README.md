[![MIT Licence](https://badges.frapsoft.com/os/mit/mit.svg?v=103)](https://opensource.org/licenses/mit-license.php)
[![stable](http://badges.github.io/stability-badges/dist/stable.svg)](http://github.com/badges/stability-badges)
[![Open Source Love](https://badges.frapsoft.com/os/v1/open-source.png?v=103)](https://github.com/ellerbrock/open-source-badge/)

# Repositorio con ejemplos del framework Spring #

En este repositorio guardo prototipos usando el framework Spring.

## ¿Por qué usar el framework spring? ##

Con este framework nos apoyamos en soluciones altamente testadas, que reducen considerablemente la cantidad de código a escribir, probar y mantener. Acelera mucho el desarrollo y puesta en producción de una solución empresarial.

Este framework es muy popular y por tanto todo lo que usemos y conozcamos de él, será muy reutilizable en el día a día, convirtiéndose en una herramienta de trabajo imprescindible.

Conseguiremos interconectar multitud de tecnologías de forma sencilla y ordenada por medio de la inyección de dependencias.

## ¿Por qué todos los nuevos ejemplos de spring se basan en spring-boot? ##

**[Spring-boot](https://projects.spring.io/spring-boot/ "spring-boot")** se puede decir que es el pilar en el que se basan los nuevos desarrollos con la plataforma spring. Adapatando la filosofia de **Convención sobre Configuración (Convention-Over-Configuration)**.

**[Spring-boot](https://projects.spring.io/spring-boot/ "spring-boot")** es un sub-proyecto de Spring. Establece un estándar en el diseño de aplicaciones basadas en **[spring](https://spring.io/projects "spring")** de modo que estén **listas para ponerse en funcionamiento tan pronto como sea posible (out of the box)**.

Se posiciona en la capa superior del framework **[spring](https://spring.io/projects "spring")**. Sigue el patrón **Convención sobre Configuración (Convention-Over-Configuration)** permitiendo al desarrollador enfocarse en el desarrollo de la aplicación, y eliminando la necesidad de estar preocupado con todos los demás aspectos de su ciclo de vida, incluyendo el despliegue y la gestión. 

> **Convención sobre Configuración (Convention-Over-Configuration)** , también conocido como **CoC** es un paradigma de programación de software que busca minimizar el número de decisiones que un desarrollador necesita hacer, ganando así en simplicidad pero no perdiendo flexibilidad por ello.

En resumen **[Spring-boot](https://projects.spring.io/spring-boot/ "spring-boot")** busca facilitarnos la creación de proyectos con el framework **[spring](https://spring.io/projects "spring")** eliminando la necesidad de crear largos archivos de configuración xml, provee configuraciones por defecto para **[spring](https://spring.io/projects "spring")** y otra gran cantidad de librerías, además provee un modelo de programación parecido a las aplicaciones java tradicionales que se inician en el método main.

Además moderniza la forma de trabajar con ***[spring](https://spring.io/projects "spring")*** asemejandose a la forma en la que los frameworks más modernos gestionan sus dependencias en un único punto, proveen del ***scaffolding*** necesario para empezar a trabajar rápidamente, permiten mecanismos para un desarrollo agil, orientado a microservicios, etc.

> ¿Quién no ha necesitado alguna vez? al desarrollar, incorporar en el mismo proyecto diferentes jars o módulos de terceros, por ejemplo librerías de ***spring-rest, spring-data, JPA, hibernate, driver de bbdd*** y si no tienes una lista de versiones compatibles, a la hora de construir o incluso durante la ejecución de la aplicación se producen errores extrañísimos de dependencias y nos vemos obligados a pasar horas buscando las versiones correctas a usar.
> 
> **Pues estos problemas con [Spring-boot](https://projects.spring.io/spring-boot/ "spring-boot") se reducen hasta casi desaparecer.**

Por sus características facilita el diseño y creación de microservicios frente a las aplicaciones monolíticas.

## Cuándo no usar microservicios ##

Los microservicios son la elección correcta si tiene un sistema que es demasiado complejo como para ser manejado como un monolito. Y esto es exactamente lo que hace que este estilo arquitectónico sea una opción válida para aplicaciones empresariales.

Como afirma Martin Fowler en su artículo sobre "Microservice Premium", el punto principal es ni siquiera considerar el uso de una arquitectura de microservicios a menos que tenga un sistema demasiado grande y complejo para ser construido como un simple monolito. Pero también es cierto que hoy en día, los procesadores multinúcleo, la computación en la nube y los dispositivos móviles son la norma, lo que significa que los sistemas completamente nuevos son sistemas distribuidos desde el principio.

Y esto también resulta en un mundo completamente diferente y más desafiante para operar. El paso lógico ahora es cambiar el pensamiento de la colaboración entre objetos en un sistema a una colaboración de sistemas de escalamiento individual de microservicios.
