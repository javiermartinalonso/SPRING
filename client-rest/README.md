[![MIT Licence](https://badges.frapsoft.com/os/mit/mit.svg?v=103)](https://opensource.org/licenses/mit-license.php)
[![stable](http://badges.github.io/stability-badges/dist/stable.svg)](http://github.com/badges/stability-badges)
[![Open Source Love](https://badges.frapsoft.com/os/v1/open-source.png?v=103)](https://github.com/ellerbrock/open-source-badge/)

# Cliente RESTful #
## Ejemplo de spring boot con RestTemplate. ##

Si necesita llamar a servicios REST remotos desde su aplicación, puede usar la clase **RestTemplate** de 
**Spring**. Dado que las instancias de **RestTemplate** a menudo necesitan personalizarse antes de ser utilizadas, **Spring Boot** no proporciona ningún bean **RestTemplate** autoconfigurado. Sin embargo, configura automáticamente una clase **RestTemplateBuilder** que se puede usar para crear instancias **RestTemplate** cuando sea necesario. La configuración automática **RestTemplateBuilder** asegurará que **HttpMessageConverters** se apliquen las instancias concretas de **RestTemplate**.



Aplicación ***client-rest***, generada con el wizard ***Spring Started Project*** del [**IDE Spring Tool Suite (STS)**](https://spring.io/tools "IDE Spring Tool Suite")




> `RestTemplateBuilder` incluye una cantidad de métodos útiles que se pueden usar para configurar rápidamente a `RestTemplate`. Por ejemplo, para agregar soporte de autenticación de BASIC puede usar `builder.basicAuthorization("user", "password").build()`.

## Lo que vas a construir ##

Construirá una aplicación que consume un servicio web RESTful.

## Página de explicación de su construcción ##

[https://spring.io/guides/gs/consuming-rest/](https://spring.io/guides/gs/consuming-rest/ "https://spring.io/guides/gs/consuming-rest/")

[https://docs.spring.io/spring-boot/docs/current/reference/html/boot-features-restclient.html](https://docs.spring.io/spring-boot/docs/current/reference/html/boot-features-restclient.html "https://docs.spring.io/spring-boot/docs/current/reference/html/boot-features-restclient.html")

## Requisitos ##

- **Alrededor de 15 minutos**
- **Un editor de texto favorito o IDE**
- **JDK 1.8 o posterior**
- **Maven 3.0+**

## Compilar y pasar los test ##

Con maven desde la carpeta raíz que contiene el .pom del módulo, ejecutamos:

    mvn clean install

## Pasos en la creación del proyecto ##

- Crear esqueleto con el wizard, seleccionando como jar y añadimos la dependencia **web** . 

		<dependency>
			<groupId>org.springframework.boot</groupId>
			<artifactId>spring-boot-starter-web</artifactId>
		</dependency>

	Con esta dependencia obtenemos el soporte de la librería **RestTemplate** y de la librería **jackson-databind**.

- Creamos una clase que funcione de entidad correspondiente al objeto json que queremos leer.

- Con el siguiente código, convertimos la respuesta de la petición json en el objeto correspondiente de nuestra aplicación cliente:

        RestTemplate restTemplate = new RestTemplate();
        Quote quote = restTemplate.getForObject("http://gturnquist-quoters.cfapps.io/api/random", Quote.class);

### Ejecuntando la aplicación ###

En este punto nuestra aplicación debe funcionar. Puesto que hemos utilizado el POM de ***spring-boot-starter-parent***, tenemos un método run de ejecución que podemos usar para iniciar la aplicación. Escribe `mvn spring-boot:run` desde línea de comandos en el directorio raíz del proyecto para iniciar la aplicación:

O puedes construir el archivo JAR con `./mvnw clean package`. Entonces puedes ejecutar el archivo JAR:

    java -jar target/client-rest-0.0.1-SNAPSHOT.jar
   
      .   ____  ___ _ _
     /\\ / ___'_ __ _ _(_)_ __  __ _ \ \ \ \
    ( ( )\___ | '_ | '_| | '_ \/ _` | \ \ \ \
     \\/  ___)| |_)| | | | | || (_| |  ) ) ) )
      '  |____| .__|_| |_|_| |_\__, | / / / /
     =========|_|==============|___/=/_/_/_/
     :: Spring Boot ::  (v2.0.0.BUILD-SNAPSHOT)
    
    2017-12-03 18:43:49.496  INFO 11632 --- [   main] e.j.client.rest.ClientRestApplication: Starting ClientRestApplication on PC-MP05RUV with PID 11632 (C:\11_GIT\SPRING\client-rest\target\classes started by javier.martin in C:\11_GIT\SPRING\client-rest)
    2017-12-03 18:43:49.499  INFO 11632 --- [   main] e.j.client.rest.ClientRestApplication: No active profile set, falling back to default profiles: default
    2017-12-03 18:43:49.566  INFO 11632 --- [   main] ConfigServletWebServerApplicationContext : Refreshing org.springframework.boot.web.servlet.context.AnnotationConfigServletWebServerApplicationContext@4facf68f: startup date [Sun Dec 03 18:43:49 CET 2017]; root of context hierarchy
    2017-12-03 18:43:51.145  INFO 11632 --- [   main] o.h.v.i.engine.ValidatorFactoryImpl  : HV000238: Temporal validation tolerance set to 0.
    2017-12-03 18:43:51.368  INFO 11632 --- [   main] o.s.b.w.embedded.tomcat.TomcatWebServer  : Tomcat initialized with port(s): 8080 (http)
    2017-12-03 18:43:51.380  INFO 11632 --- [   main] o.apache.catalina.core.StandardService   : Starting service [Tomcat]
    2017-12-03 18:43:51.382  INFO 11632 --- [   main] org.apache.catalina.core.StandardEngine  : Starting Servlet Engine: Apache Tomcat/8.5.23
    2017-12-03 18:43:51.393  INFO 11632 --- [ost-startStop-1] o.a.catalina.core.AprLifecycleListener   : The APR based Apache Tomcat Native library which allows optimal performance in production environments was not found on the java.library.path: [C:\Program Files\Java\jre1.8.0_101\bin;C:\Windows\Sun\Java\bin;C:\Windows\system32;C:\Windows;C:/Program Files/Java/jre1.8.0_101/bin/server;C:/Program Files/Java/jre1.8.0_101/bin;C:/Program Files/Java/jre1.8.0_101/lib/amd64;C:\tools\ruby23\bin;C:\oraclexe\app\oracle\product\11.2.0\server\bin;C:\ProgramData\Oracle\Java\javapath;C:\Program Files (x86)\Newgen Common\CommonDlls;C:\Program Files\Java\jdk1.7.0_79\bin;C:\Windows\system32;C:\Windows;C:\Windows\System32\Wbem;C:\Windows\System32\WindowsPowerShell\v1.0\;C:\Program Files (x86)\ATI Technologies\ATI.ACE\Core-Static;C:\Program Files\apache-maven-3.3.9\bin;C:\Program Files\TortoiseSVN\bin;C:\Program Files (x86)\Bitvise SSH Client;C:\Program Files\apache-ant-1.9.7\bin;C:\Program Files\Java\jdk1.8.0_73\bin;C:\Program Files\MIT\Kerberos\bin;C:\Program Files\nodejs\;C:\ProgramData\chocolatey\bin;C:\Program Files\SourceGear\Common\DiffMerge\;C:\Program Files (x86)\Skype\Phone\;C:\Program Files\TortoiseGit\bin;C:\HashiCorp\Vagrant\bin;C:\Program Files\Git\cmd;C:\Python27;C:\Python27\Scripts;C:\Users\javier.martin\AppData\Roaming\npm;C:\Program Files (x86)\Microsoft VS Code\bin;C:\Users\javier.martin\AppData\Local\atom\bin;C:\Program Files\Docker Toolbox;C:\spring-tool-suite-3.9.1.RELEASE-e4.7.1a-win32-x86_64\sts-bundle\sts-3.9.1.RELEASE;;.]
    2017-12-03 18:43:51.531  INFO 11632 --- [ost-startStop-1] o.a.c.c.C.[Tomcat].[localhost].[/]   : Initializing Spring embedded WebApplicationContext
    2017-12-03 18:43:51.531  INFO 11632 --- [ost-startStop-1] o.s.web.context.ContextLoader: Root WebApplicationContext: initialization completed in 1971 ms
    2017-12-03 18:43:51.912  INFO 11632 --- [ost-startStop-1] o.s.b.w.servlet.ServletRegistrationBean  : Mapping servlet: 'dispatcherServlet' to [/]
    2017-12-03 18:43:51.918  INFO 11632 --- [ost-startStop-1] o.s.b.w.servlet.FilterRegistrationBean   : Mapping filter: 'characterEncodingFilter' to: [/*]
    2017-12-03 18:43:51.918  INFO 11632 --- [ost-startStop-1] o.s.b.w.servlet.FilterRegistrationBean   : Mapping filter: 'hiddenHttpMethodFilter' to: [/*]
    2017-12-03 18:43:51.919  INFO 11632 --- [ost-startStop-1] o.s.b.w.servlet.FilterRegistrationBean   : Mapping filter: 'httpPutFormContentFilter' to: [/*]
    2017-12-03 18:43:51.919  INFO 11632 --- [ost-startStop-1] o.s.b.w.servlet.FilterRegistrationBean   : Mapping filter: 'requestContextFilter' to: [/*]
    2017-12-03 18:43:52.265  INFO 11632 --- [   main] o.h.v.i.engine.ValidatorFactoryImpl  : HV000238: Temporal validation tolerance set to 0.
    2017-12-03 18:43:52.461  INFO 11632 --- [   main] s.w.s.m.m.a.RequestMappingHandlerAdapter : Looking for @ControllerAdvice: org.springframework.boot.web.servlet.context.AnnotationConfigServletWebServerApplicationContext@4facf68f: startup date [Sun Dec 03 18:43:49 CET 2017]; root of context hierarchy
    2017-12-03 18:43:52.510  INFO 11632 --- [   main] s.w.s.m.m.a.RequestMappingHandlerMapping : Mapped "{[/error]}" onto public org.springframework.http.ResponseEntity<java.util.Map<java.lang.String, java.lang.Object>> org.springframework.boot.autoconfigure.web.servlet.error.BasicErrorController.error(javax.servlet.http.HttpServletRequest)
    2017-12-03 18:43:52.524  INFO 11632 --- [   main] s.w.s.m.m.a.RequestMappingHandlerMapping : Mapped "{[/error],produces=[text/html]}" onto public org.springframework.web.servlet.ModelAndView org.springframework.boot.autoconfigure.web.servlet.error.BasicErrorController.errorHtml(javax.servlet.http.HttpServletRequest,javax.servlet.http.HttpServletResponse)
    2017-12-03 18:43:52.556  INFO 11632 --- [   main] o.s.w.s.handler.SimpleUrlHandlerMapping  : Mapped URL path [/webjars/**] onto handler of type [class org.springframework.web.servlet.resource.ResourceHttpRequestHandler]
    2017-12-03 18:43:52.556  INFO 11632 --- [   main] o.s.w.s.handler.SimpleUrlHandlerMapping  : Mapped URL path [/**] onto handler of type [class org.springframework.web.servlet.resource.ResourceHttpRequestHandler]
    2017-12-03 18:43:52.595  INFO 11632 --- [   main] o.s.w.s.handler.SimpleUrlHandlerMapping  : Mapped URL path [/**/favicon.ico] onto handler of type [class org.springframework.web.servlet.resource.ResourceHttpRequestHandler]
    2017-12-03 18:43:52.709  INFO 11632 --- [   main] o.s.j.e.a.AnnotationMBeanExporter: Registering beans for JMX exposure on startup
    2017-12-03 18:43:52.770  INFO 11632 --- [   main] o.s.b.w.embedded.tomcat.TomcatWebServer  : Tomcat started on port(s): 8080 (http) with context path ''
    2017-12-03 18:43:52.774  INFO 11632 --- [   main] e.j.client.rest.ClientRestApplication: Started ClientRestApplication in 3.645 seconds (JVM running for 5.351)
    2017-12-03 18:43:53.186  INFO 11632 --- [   main] e.j.client.rest.ClientRestApplication: Quote{type='success', value=Value{id=10, quote='Really loving Spring Boot, makes stand alone Spring apps easy.'}}


¡Felicitaciones! Acaba de desarrollar un cliente para consumir un servicio web basado en RESTful con spring.




http://www.baeldung.com/rest-template

https://anotherdayanotherbug.wordpress.com/2015/05/05/implementar-un-cliente-rest-con-spring-resttemplate/?blogsub=confirming#subscribe-blog
