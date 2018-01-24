[![MIT Licence](https://badges.frapsoft.com/os/mit/mit.svg?v=103)](https://opensource.org/licenses/mit-license.php)
[![stable](http://badges.github.io/stability-badges/dist/stable.svg)](http://github.com/badges/stability-badges)
[![Open Source Love](https://badges.frapsoft.com/os/v1/open-source.png?v=103)](https://github.com/ellerbrock/open-source-badge/)

# Ejemplo de spring boot selenyum bonita #



Aplicación ******, generada con el wizard ***Spring Started Project*** del [**IDE Spring Tool Suite (STS)**](https://spring.io/tools "IDE Spring Tool Suite")


## Lo que vas a construir ##

Spring Boot and Thymeleaf with Maven
====================================

- Spring Boot with Maven and Spring IO Platform for dependency management
- Web application (WAR) packaging as well as self-contained JAR
- Thymeleaf with Java 8 Time (Java8TimeDialect)
- WebJars
- Selenium configuration included
- Maven Wrapper included

## Página de explicación de su construcción ##

http://blog.codeleak.pl/2015/03/spring-boot-integration-testing-with.html
https://www.guru99.com/accessing-forms-in-webdriver.html
https://examples.javacodegeeks.com/enterprise-java/selenium/selenium-junit-example/


## Presentando WebDriver ##

La nueva característica principal en Selenium 2.0 es la integración de la API de WebDriver. WebDriver está diseñado para proporcionar una interfaz de programación más simple y concisa, además de abordar algunas limitaciones en la API de Selenium-RC. Selenium-WebDriver se desarrolló para admitir mejor las páginas web dinámicas donde los elementos de una página pueden cambiar sin que la página se vuelva a cargar. El objetivo de WebDriver es proporcionar una API orientada a objetos bien diseñada que proporcione soporte mejorado para los modernos problemas avanzados de prueba de aplicaciones web.

Selenium-WebDriver realiza llamadas directas al navegador utilizando el soporte nativo de cada navegador para la automatización. La forma en que se realizan estas llamadas directas y las características que admiten depende del navegador que está utilizando. Maneja el navegador directamente usando el soporte incorporado del navegador para la automatización.


## WebDriver y Selenium-Server ##

Puede o no necesitar el Servidor de Selenio, dependiendo de cómo pretenda utilizar Selenium-WebDriver. Si su navegador y todas las pruebas se ejecutarán en la misma máquina, y sus pruebas solo usan la API de WebDriver, entonces no necesita ejecutar el Servidor Selenium; WebDriver ejecutará el navegador directamente.

Sin embargo, existen algunas razones para usar el servidor Selenium con Selenium-WebDriver.

Está utilizando Selenium-Grid para distribuir sus pruebas en varias máquinas o máquinas virtuales (VM).
Desea conectarse a una máquina remota que tiene una versión de navegador particular que no está en su máquina actual.
No está utilizando los enlaces de Java (es decir, Python, C # o Ruby) y le gustaría usar HtmlUnit Driver



## Configuración de un proyecto Selenium-WebDriver ##

Para instalar Selenium, significa configurar un proyecto en un desarrollo para que pueda escribir un programa usando Selenium. Cómo lo hace depende de su lenguaje de programación y su entorno de desarrollo.

## Java ##

La forma más fácil de configurar un proyecto Selenium 2.0 Java es usar Maven. Maven descargará los enlaces de Java (la biblioteca de cliente Java de Selenium 2.0) y todas sus dependencias, y creará el proyecto para usted, utilizando un archivo maven pom.xml (configuración de proyecto). Una vez que haya hecho esto, puede importar el proyecto maven a su IDE preferido, IntelliJ IDEA o Eclipse.




> Nota: se requieren pasos adicionales para utilizar ChromeDriver , Opera Driver , Android Driver y iOS Driver


## Requisitos ##

- **Alrededor de 15 minutos**
- **Un editor de texto favorito o IDE**
- **JDK 1.8 o posterior**
- **Maven 3.0+**

- JDK 8 and JAVA_HOME environment variable set 

## Compilar y pasar los test ##

Con maven desde la carpeta raíz que contiene el .pom del módulo, ejecutamos:



## 41.1 Dependencias del alcance de la prueba ##

Si usa el spring-boot-starter-test'Starter' (en el test scope), encontrará las siguientes bibliotecas proporcionadas:

- JUnit  : el estándar de facto para probar unidades de aplicaciones Java.
- Spring Test & Spring Boot Test Utilidades y soporte de prueba de integración para aplicaciones Spring Boot.
- AssertJ  Una biblioteca de aserciones fluidas.
- Hamcrest  : una biblioteca de objetos matcher (también conocidos como restricciones o predicados).
- Mockito  : un marco de burla de Java.
- JSONassert  : una biblioteca de afirmaciones para JSON.
- JsonPath  XPath para JSON.

[Note]
Por defecto, Spring Boot usa Mockito 1.x. Sin embargo, también es posible usar 2.x si lo desea.

Estas son bibliotecas comunes que generalmente encontramos útiles al escribir pruebas. Puede agregar dependencias de prueba adicionales si estas no se ajustan a sus necesidades.

    mvn clean install

Building the project
--------------------

Clone the repository:

> git clone https://github.com/kolorobot/spring-boot-thymeleaf

Navigate to the newly created folder:

> cd spring-boot-thymeleaf

Run the project with:

> mvnw clean spring-boot:run

To package the project run:

> mvnw clean package


## Pasos en la creación del proyecto ##

- Crear esqueleto con el wizard, seleccionando como war y añadimos la dependencia. 

	

### Ejecuntando la aplicación ###

En este punto nuestra aplicación debe funcionar. Puesto que hemos utilizado el POM de ***spring-boot-starter-parent***, tenemos un método run de ejecución que podemos usar para iniciar la aplicación. Escribe `mvn spring-boot:run` desde línea de comandos en el directorio raíz del proyecto para iniciar la aplicación:

O puedes construir el archivo JAR con `./mvnw clean package`. Entonces puedes ejecutar el archivo JAR:


## Referencias ##

https://docs.spring.io/spring-boot/docs/current/reference/htmlsingle/#boot-features-testing
https://docs.spring.io/spring/docs/current/spring-framework-reference/testing.html
http://www.seleniumhq.org/docs/03_webdriver.jsp


http://blog.codeleak.pl/2015/03/spring-boot-integration-testing-with.html

https://testingbaires.com/selenium-webdriver-y-buscar-elementos-en-la-pagina-web/