# inventory-batch


## Build & development

Desde linea de comandos, nos situamos en el directorio raíz del proyecto:

- Compilar primero spring-batch-excel para que compile en local:

Opción 1) compilar el parent. REVISAR ESTO

OPCION2) 
		cd ../spring-batch-excel
		`mvn -Dmaven.test.skip=true clean install

- compilar con maven en la maquina local

		`mvn -Dmaven.test.skip=true clean install

- compilar con maven para el entorno de integracion

		mvn -Dmaven.test.skip=true package -P integration

para obtener un fichero jar en la carpeta **\inventory-batch\target\inventory-batch.war** y a continuación **ejecutar como una aplicacion java**.

REVISAR ESTO

## Funcionalidad del módulo ##

Este módulo ejecuta un batch de carga de datos en inventario de tecnologias WIMAX, CDMA y POTS, a través de unos excels de entrada, atacando el API del inventario para crear los recursos y a continuación genera un excel de salida con el resultado de la carga. 
