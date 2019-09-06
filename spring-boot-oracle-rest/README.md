# sigo-sim-stock-service



## Build & development

Desde linea de comandos, nos situamos en el directorio raíz del proyecto:

- Agregar el driver de oracle al repositorio local para que compile en local:
		
		mvn install:install-file -DgroupId=com.oracle -DartifactId=ojdbc6 -Dversion=11.2.0 -Dpackaging=jar -Dfile=.\lib\oracle\ojdbc6-11.2.0.jar -DgeneratePom=true

- compilar con maven en la maquina local

		`mvn -Dmaven.test.skip=true clean install

- compilar con maven para el entorno de integracion

		mvn -Dmaven.test.skip=true package -P integration

para obtener un fichero jar en la carpeta **\sigo-sim-stock-service\target\sigo-sim-stock-service.war** y a continuación **desplegar en un servidor de aplicaciones web**.

## Funcionalidad del módulo ##

Este módulo expone mediante rest las tablas de sigo que gestionan la recepcion de SIMS en el ERP. 

Proporciona los siguientes servicios REST:

- POST	**/services/v1.0/simControlFile -> Recibe un objeto simControlFile, que da de alta o actualiza según exista el registro en la bbdd de ERP.
	
- POST	**/services/v1.0/simSerialNumbers -> Recibe un objeto simSerialNumbers, que da de alta o actualiza según exista el registro en la bbdd de ERP.