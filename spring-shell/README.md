[![MIT Licence](https://badges.frapsoft.com/os/mit/mit.svg?v=103)](https://opensource.org/licenses/mit-license.php)
[![stable](http://badges.github.io/stability-badges/dist/stable.svg)](http://github.com/badges/stability-badges)
[![Open Source Love](https://badges.frapsoft.com/os/v1/open-source.png?v=103)](https://github.com/ellerbrock/open-source-badge/)

# Ejemplo sencillo de spring shell #


Aplicación ***Spring-boot+ spring-shell***, generada con el wizard ***Spring Started Project*** del [**IDE Spring Tool Suite (STS)**](https://spring.io/tools "IDE Spring Tool Suite")

Esta guía lo guiará por el proceso de contruir un programa java que interactua con el usuario usando la línea de comandos con el módulo shell de Spring.

Partimos de la guia [https://www.youtube.com/watch?v=h6nMjjxJWjk&feature=em-uploademail](https://www.youtube.com/watch?v=h6nMjjxJWjk&feature=em-uploademail "https://www.youtube.com/watch?v=h6nMjjxJWjk&feature=em-uploademail").

## Lo que vas a construir ##

Construirá un programa java que interactua con el usuario desde línea de comandos usando el shell de spring.
esto puede ser interesante para realizar una aplicación que queremos ejecutar desde línea de comandos de linux directamente sin ningún tipo de interfaz gráfica.

## Página de explicación de su construcción ##

Partimos de la guia [https://www.youtube.com/watch?v=h6nMjjxJWjk&feature=em-uploademail](https://www.youtube.com/watch?v=h6nMjjxJWjk&feature=em-uploademail "https://www.youtube.com/watch?v=h6nMjjxJWjk&feature=em-uploademail").

## Requisitos ##

- **Alrededor de 15 minutos**
- **Un editor de texto favorito o IDE**
- **JDK 1.8 o posterior**
- **Maven 3.0 o superior**

## Compilar y pasar los test ##

Con maven desde la carpeta raiz que contiene el .pom del módulo, ejecutamos:

    mvn clean install

## Pasos en la creación del proyecto ##

- Crear esqueleto con el wizard, seleccionando como jar. 

- como dependencias seleccionamos spring-shell y Lombok.

- implementamos métodos de la consola.

- en el pom seleccionar como tipo de destino .jar.

- compilar y ejecutar.


> No funcionan los colores en la consola

### Ejecuntando la aplicación ###

En este punto nuestra aplicación debe funcionar. Puesto que hemos utilizado el POM de ***spring-boot-starter-parent***, tenemos un método run de ejecución que podemos usar para iniciar la aplicación. Escribe `mvn spring-boot:run` desde línea de comandos en el directorio raíz del proyecto para iniciar la aplicación:

O puedes construir el archivo JAR con `./mvnw clean package`. Entonces puedes ejecutar el archivo JAR:

`mvnw -DskipTests=true clean install && java -jar target/spring-shell-0.0.1-SNAPSHOT.jar`


	
	  .   ____          _            __ _ _
	 /\\ / ___'_ __ _ _(_)_ __  __ _ \ \ \ \
	( ( )\___ | '_ | '_| | '_ \/ _` | \ \ \ \
	 \\/  ___)| |_)| | | | | || (_| |  ) ) ) )
	  '  |____| .__|_| |_|_| |_\__, | / / / /
	 =========|_|==============|___/=/_/_/_/
	 :: Spring Boot ::  (v2.0.0.BUILD-SNAPSHOT)
	
	2017-11-06 11:37:36.659  INFO 11212 --- [           main] e.j.springshell.SpringShellApplication   : Starting SpringShellApplication v0.0.1-SNAPSHOT on PC-MP05R
	UV with PID 11212 (C:\11_GIT\SPRING\spring-shell\target\spring-shell-0.0.1-SNAPSHOT.jar started by javier.martin in C:\11_GIT\SPRING\spring-shell)
	2017-11-06 11:37:36.671  INFO 11212 --- [           main] e.j.springshell.SpringShellApplication   : No active profile set, falling back to default profiles: de
	fault
	2017-11-06 11:37:36.822  INFO 11212 --- [           main] ConfigServletWebServerApplicationContext : Refreshing org.springframework.boot.web.servlet.context.Ann
	otationConfigServletWebServerApplicationContext@67117f44: startup date [Mon Nov 06 11:37:36 CET 2017]; root of context hierarchy
	2017-11-06 11:37:40.271  INFO 11212 --- [           main] o.s.b.w.embedded.tomcat.TomcatWebServer  : Tomcat initialized with port(s): 8080 (http)
	2017-11-06 11:37:40.305  INFO 11212 --- [           main] o.apache.catalina.core.StandardService   : Starting service [Tomcat]
	2017-11-06 11:37:40.308  INFO 11212 --- [           main] org.apache.catalina.core.StandardEngine  : Starting Servlet Engine: Apache Tomcat/8.5.23
	2017-11-06 11:37:40.355  INFO 11212 --- [ost-startStop-1] o.a.catalina.core.AprLifecycleListener   : The APR based Apache Tomcat Native library which allows opt
	imal performance in production environments was not found on the java.library.path: [C:\ProgramData\Oracle\Java\javapath;C:\Windows\Sun\Java\bin;C:\Windows\syst
	em32;C:\Windows;C:\tools\ruby23\bin;C:\oraclexe\app\oracle\product\11.2.0\server\bin;C:\ProgramData\Oracle\Java\javapath;C:\Program Files (x86)\Newgen Common\Co
	mmonDlls;C:\Program Files\Java\jdk1.7.0_79\bin;C:\Windows\system32;C:\Windows;C:\Windows\System32\Wbem;C:\Windows\System32\WindowsPowerShell\v1.0\;C:\Program Fi
	les (x86)\ATI Technologies\ATI.ACE\Core-Static;C:\Program Files\apache-maven-3.3.9\bin;C:\Program Files\TortoiseSVN\bin;C:\Program Files (x86)\Bitvise SSH Clien
	t;C:\Program Files\apache-ant-1.9.7\bin;C:\Program Files\Java\jdk1.8.0_73\bin;C:\Program Files\MIT\Kerberos\bin;C:\Program Files\nodejs\;C:\ProgramData\chocolat
	ey\bin;C:\Program Files\SourceGear\Common\DiffMerge\;C:\Program Files (x86)\Skype\Phone\;C:\Program Files\TortoiseGit\bin;C:\HashiCorp\Vagrant\bin;C:\Program Fi
	les\Git\cmd;C:\Python27;C:\Python27\Scripts;C:\Users\javier.martin\AppData\Roaming\npm;C:\Program Files (x86)\Microsoft VS Code\bin;C:\Users\javier.martin\AppDa
	ta\Local\atom\bin;C:\Program Files\Docker Toolbox;.]
	2017-11-06 11:37:40.510  INFO 11212 --- [ost-startStop-1] o.a.c.c.C.[Tomcat].[localhost].[/]       : Initializing Spring embedded WebApplicationContext
	2017-11-06 11:37:40.510  INFO 11212 --- [ost-startStop-1] o.s.web.context.ContextLoader            : Root WebApplicationContext: initialization completed in 369
	3 ms
	2017-11-06 11:37:40.746  INFO 11212 --- [ost-startStop-1] o.s.b.w.servlet.ServletRegistrationBean  : Mapping servlet: 'dispatcherServlet' to [/]
	2017-11-06 11:37:40.777  INFO 11212 --- [ost-startStop-1] o.s.b.w.servlet.FilterRegistrationBean   : Mapping filter: 'characterEncodingFilter' to: [/*]
	2017-11-06 11:37:40.779  INFO 11212 --- [ost-startStop-1] o.s.b.w.servlet.FilterRegistrationBean   : Mapping filter: 'hiddenHttpMethodFilter' to: [/*]
	2017-11-06 11:37:40.781  INFO 11212 --- [ost-startStop-1] o.s.b.w.servlet.FilterRegistrationBean   : Mapping filter: 'httpPutFormContentFilter' to: [/*]
	2017-11-06 11:37:40.782  INFO 11212 --- [ost-startStop-1] o.s.b.w.servlet.FilterRegistrationBean   : Mapping filter: 'requestContextFilter' to: [/*]
	2017-11-06 11:37:41.671  INFO 11212 --- [           main] s.w.s.m.m.a.RequestMappingHandlerAdapter : Looking for @ControllerAdvice: org.springframework.boot.web
	.servlet.context.AnnotationConfigServletWebServerApplicationContext@67117f44: startup date [Mon Nov 06 11:37:36 CET 2017]; root of context hierarchy
	2017-11-06 11:37:41.896  INFO 11212 --- [           main] s.w.s.m.m.a.RequestMappingHandlerMapping : Mapped "{[/error]}" onto public org.springframework.http.Re
	sponseEntity<java.util.Map<java.lang.String, java.lang.Object>> org.springframework.boot.autoconfigure.web.servlet.error.BasicErrorController.error(javax.servle
	t.http.HttpServletRequest)
	2017-11-06 11:37:41.899  INFO 11212 --- [           main] s.w.s.m.m.a.RequestMappingHandlerMapping : Mapped "{[/error],produces=[text/html]}" onto public org.sp
	ringframework.web.servlet.ModelAndView org.springframework.boot.autoconfigure.web.servlet.error.BasicErrorController.errorHtml(javax.servlet.http.HttpServletReq
	uest,javax.servlet.http.HttpServletResponse)
	2017-11-06 11:37:41.952  INFO 11212 --- [           main] o.s.w.s.handler.SimpleUrlHandlerMapping  : Mapped URL path [/webjars/**] onto handler of type [class o
	rg.springframework.web.servlet.resource.ResourceHttpRequestHandler]
	2017-11-06 11:37:41.953  INFO 11212 --- [           main] o.s.w.s.handler.SimpleUrlHandlerMapping  : Mapped URL path [/**] onto handler of type [class org.sprin
	gframework.web.servlet.resource.ResourceHttpRequestHandler]
	2017-11-06 11:37:42.039  INFO 11212 --- [           main] o.s.w.s.handler.SimpleUrlHandlerMapping  : Mapped URL path [/**/favicon.ico] onto handler of type [cla
	ss org.springframework.web.servlet.resource.ResourceHttpRequestHandler]
	2017-11-06 11:37:42.833  INFO 11212 --- [           main] o.s.j.e.a.AnnotationMBeanExporter        : Registering beans for JMX exposure on startup
	2017-11-06 11:37:42.965  INFO 11212 --- [           main] o.s.b.w.embedded.tomcat.TomcatWebServer  : Tomcat started on port(s): 8080 (http)
	2017-11-06 11:37:42.973  INFO 11212 --- [           main] e.j.springshell.SpringShellApplication   : Started SpringShellApplication in 7.11 seconds (JVM running
	 for 7.864)
	shell:>



## principales comandos: ##

- help: nos indica los comandos disponibles:

		shell:>help
		AVAILABLE COMMANDS
		
		Built-In Commands
		        clear: Clear the shell screen.
		        exit, quit: Exit the shell.
		        help: Display help about available commands.
		        script: Read and execute commands from a file.
		        stacktrace: Display the full stacktrace of the last error.
		
		Connection Commands
		        connect: Connect to the PersonService