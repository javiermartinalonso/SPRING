## SPRING BOOT EXAMPLE

### references to Oficial page

      http://projects.spring.io/spring-boot/

Spring Boot Reference Guide:

      http://docs.spring.io/spring-boot/docs/current-SNAPSHOT/reference/htmlsingle/

getting-started

      http://docs.spring.io/spring-boot/docs/current-SNAPSHOT/reference/htmlsingle/#getting-started

Sample application demonstrating how to do use spring-boot.

This ApplicationContext will start an in-process Apache Directory Server instance, automatically populated
with some test data. The data will be reset every time the application is restarted.

### Running the example
At this point our application should work. Since we have used the spring-boot-starter-parent POM we have a useful run goal that we can use to start the application. Type mvn spring-boot:run from the root project directory to start the application:

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

If you open a web browser to localhost:8080 you should see the following output:

    Hello World!

To gracefully exit the application hit ctrl-c.
