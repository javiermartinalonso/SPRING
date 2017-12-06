https://blog.frankel.ch/designing-your-own-spring-boot-starter/2/#gsc.tab=0
https://blog.frankel.ch/designing-your-own-spring-boot-starter/1/#gsc.tab=0

https://www.youtube.com/watch?v=zSJFx0iyrq4&feature=youtu.be

Estudio de cómo funciona la configuración de arranque de spring boot y por tanto descubriendo la magia detrás de spring-boot ya que es así como funciona spring-boot para darnos esta flexibilidad y facilidad de manejo..

Como ejemplo, usaremos XStream , un serializador XML / JSON (de) de pura materia prima ofrecido por Thoughtworks. Se aconseja a los lectores que solo usan JAXB y Jackson echar un vistazo a XStream, es extremadamente eficiente y su API es bastante fácil de usar.



#Custom Spring Boot starter demo

This project is the code meant to demo how to create a Spring Boot starter.

The explanation of all steps are in those 2 articles:

* [Part 1 - Theory](https://blog.frankel.ch/designing-your-own-spring-boot-starter-part-1)
* [Part 2 - Code and explanations](https://blog.frankel.ch/designing-your-own-spring-boot-starter-part-2)


### Ejecuntando la aplicación ###

En este punto nuestra aplicación debe funcionar. Puesto que hemos utilizado el POM de ***spring-boot-starter-parent***, tenemos un método run de ejecución que podemos usar para iniciar la aplicación. Escribe `mvn spring-boot:run` desde línea de comandos en el directorio raíz del proyecto para iniciar la aplicación:

O puedes construir el archivo JAR con `./mvnw clean package`. Entonces puedes ejecutar el archivo JAR:

## Referencias ##

https://geowarin.github.io/understanding-spring-boot.html
https://docs.spring.io/spring-boot/docs/current/reference/html/boot-features-developing-auto-configuration.html