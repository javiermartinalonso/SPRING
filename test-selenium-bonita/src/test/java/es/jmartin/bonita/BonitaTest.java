package es.jmartin.bonita;

import java.util.List;

import org.junit.After;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import es.jmartin.selenium.support.SeleniumTest;

@RunWith(SpringRunner.class)
@SpringBootTest(properties = "server.port=9000", webEnvironment = SpringBootTest.WebEnvironment.DEFINED_PORT)
// @SeleniumTest(driver = FirefoxDriver.class, baseUrl = "http://localhost:8080/bonita/login.jsp")
@SeleniumTest(driver = ChromeDriver.class, baseUrl = "http://localhost:8080/bonita/login.jsp")
public class BonitaTest {

	@Autowired
	private WebDriver driver;

	public boolean isAlreadyLogIn = false;
	
	public WebDriverWait wait;

	 @Before
	 public void setUp() throws Exception {
		 //defino el tiempo de las esperas a que aparezcan los elementos en la pagina
		 wait = new WebDriverWait(driver, 10);
	 }

	@BeforeClass
	public static void setupClass() {

		String strFile = ClassLoader.getSystemClassLoader().getResource("seleniumDrivers/chromedriver-2.35.exe")
				.getFile();

		System.setProperty("webdriver.chrome.driver", strFile);
		// System.setProperty("webdriver.chrome.driver","C:\\selenium\\chromedriver.exe");
		// System.setProperty("webdriver.gecko.driver","C:\\selenium\\geckodriver.exe");
		
		
	}

	@After
	public void teardown() {
		// cerrar el navegador
		/*
		 * if (driver != null) { driver.quit(); }
		 */
	}

	@Test
	public void prueba() {

		// login
		logIn("walter.bates", "bpm");

		// Filtrar tareas por proceso
		filtrarTareas("Selenium", "1.0", "Step1");
		// filtrarTareas("", "1.0", "Step1");

		// hacer una tarea
		// Revisar la lista y coger una tarea concreta. Por ejemplo la primera.

		selecTask();

		openPopupDoTask();


		// nos movemos a la ventana modal activa
		// asegurarse de que se ha cargado la pagina
		(new WebDriverWait(driver, 10)).until(new ExpectedCondition<Boolean>() {
			public Boolean apply(WebDriver iframes) {
				// return driver.findElements(By.id("bonitaframe")).get(1).isDisplayed();
				return driver.findElements(By.id("bonitaframe")).size() == 2;
			}
		});

		// Nos movemos al iframe de profundidad 2
		driver.switchTo().frame(driver.findElements(By.id("bonitaframe")).get(1));

		
		
		WebElement input1 = wait.until(ExpectedConditions.elementToBeClickable(By.name("pbInput0")));
		//input1.click();           
		input1.sendKeys("69");
		
/*		
		// asegurarse de que se ha cargado la pagina
		(new WebDriverWait(driver, 10)).until(new ExpectedCondition<Boolean>() {
			public Boolean apply(WebDriver logout) {
				// sería interesante obtener el boton por la etiqueta u algo que lo identifique
				// univocamente, aunque modifiquemos el formulario metiendo nuevos inputs.
				// label[text()='Seaside & Country Homes']/preceding-sibling::input[@type='checkbox']
				return driver.findElement(By.name("pbInput0")).isDisplayed();
			}
		});

		WebElement input1 = driver.findElement(By.name("pbInput0"));
		input1.sendKeys("69");
*/
		// Boton de cerrar la pantalla modal
		By buttonCloseXPath = By.xpath("//button[@title='Close']");
		
		waitLoadBonitaIframe();
		WebElement buttonClose = wait.until(ExpectedConditions.elementToBeClickable(buttonCloseXPath));
		buttonClose.click();  
		
/*		
		// asegurarse de que se ha cargado la pagina
		// String buttonCloseCssSel = "button.pull-right.close.Modal-dismiss";

		(new WebDriverWait(driver, 10)).until(new ExpectedCondition<Boolean>() {
			public Boolean apply(WebDriver logout) {
				waitLoadBonitaIframe();

				// return !driver.findElements(By.cssSelector(buttonCloseCssSel)).isEmpty();
				return driver.findElement(buttonCloseXPath).isDisplayed();
			}
		});

		// driver.findElements(By.cssSelector(buttonCloseCssSel)).get(0).click();
		driver.findElement(buttonCloseXPath).click();
*/
		// logout
		// logOut();
	}

	/**
	 * Hacemos login en el portal de bonita.
	 * Necesitamos el user y pass
	 * @param userID
	 * @param password
	 */
	public void logIn(String userID, String password) {
		// nos aseguramos de estar en el contenedor principal
		driver.switchTo().defaultContent();

		// To check If already login previously then don't execute this function.
		if (!isAlreadyLogIn) {
			// If Not login then login In to your account.
			// login
			WebElement user = driver.findElement(By.id("username"));
			WebElement pass = driver.findElement(By.id("password"));

			// walter.bates
			// bpm
			user.sendKeys(userID);
			pass.sendKeys(password);

			// To submit form.
			// You can use any other Input field's(user) xpath too In bellow given syntax.
			pass.submit();
			isAlreadyLogIn = true;
		}
	}

	/**
	 * hacemos logout en el portal de bonita
	 */
	public void logOut() {

		// nos aseguramos de estar en el contenedor principal
		driver.switchTo().defaultContent();

		// logout
		//Una opción es lanzar la siguiente url:
		// http://localhost:8080/bonita/logoutservice
		
		//otra opcion es hacer click en el boton de logout del menu superior
		// asegurarse de que se ha cargado la pagina
		(new WebDriverWait(driver, 10)).until(new ExpectedCondition<Boolean>() {
			public Boolean apply(WebDriver logout) {
				return driver.findElement(By.partialLinkText("Walter Bates")).isDisplayed();
			}
		});

		// debemos hacer click en el nombre del usuario, para desplegar el boton de
		// logout
		WebElement logout = driver.findElement(By.partialLinkText("Walter Bates"));
		logout.click();
		// ahora ya podemos hacer click en el logout
		logout = driver.findElement(By.partialLinkText("Logout"));
		logout.click();
		isAlreadyLogIn = false;
	}

	
	
	/**
	 * filtramos por proceso, para ello necesitamos el nombre y la versión del proceso.
	 * Si no indicamos un nombre, no filtramos
	 * @param strProceso
	 * @param strVersion
	 */
	public void filtrarProceso(String strProceso, String strVersion) {

		//Si no indicamos proceso no filtramos nada
		if (strProceso.isEmpty()) {
			return;
		}

		//esperamos a que se haya cargado el contenedor principal que esta contenido dentro de un iframe
		waitLoadBonitaIframe();

		// tenemos que buscar el botón de desplegar los procesos instalados.
		String desplegableProcesosCssSel = "button.btn.btn-primary.dropdown-toggle";

		// tenemos que hacer clik en el botón de desplegar los procesos instalados.	
		
		WebElement searchButton = wait.until(ExpectedConditions.elementToBeClickable(By.cssSelector(desplegableProcesosCssSel)));
		searchButton.click();	
		
		/*
		// asegurarse de que se ha cargado la pagina
		(new WebDriverWait(driver, 10)).until(new ExpectedCondition<Boolean>() {
			public Boolean apply(WebDriver webDriver) {
				waitLoadBonitaIframe();
				return !driver.findElements(By.cssSelector(desplegableProcesosCssSel)).isEmpty();
			}
		});

		driver.findElements(By.cssSelector(desplegableProcesosCssSel)).get(0).click();			
		 */		
		
		
//		waitLoadBonitaIframe();

		// hacer click en el proceso por el que queremos filtrar de la lista de elementos del desplegable 	
		String procesoLinkText = strProceso.concat(" ").concat(strVersion);
		WebElement filtrarProceso = (new WebDriverWait(driver, 10))
				  .until(ExpectedConditions.elementToBeClickable(By.linkText(procesoLinkText)));	  
		filtrarProceso.click();
	
		/*		
		// asegurarse de que se ha desplegado la lista de procesos para poder elegir el proceso que queremos filtrar
		(new WebDriverWait(driver, 10)).until(new ExpectedCondition<Boolean>() {
			public Boolean apply(WebDriver filtrarProceso2) {
				waitLoadBonitaIframe();
				// tenemos que hacer clcik en el botón de desplegar los procesos instalados.
				//driver.findElements(By.cssSelector(desplegableProcesosCssSel)).get(0).click();
				// buscamos en el desplegable el proceso por el que queremos filtrar
				return driver.findElement(By.linkText(procesoLinkText)).isDisplayed();
			}
		});

		// hacer click en el desplegable el proceso por el que queremos filtrar
		WebElement filtrarProceso = driver.findElement(By.linkText(procesoLinkText));
				filtrarProceso.click();
		 */
	}

	
	/**
	 * Filtramos las tareas pendientes por proceso, versión y nombre de la tarea.
	 * @param strProceso
	 * @param strVersion
	 * @param strTarea
	 */
	public void filtrarTareas(String strProceso, String strVersion, String strTarea) {

		// Filtro por proceso
		filtrarProceso(strProceso, strVersion);

		// asegurarse de que se ha cargado la pagina
		(new WebDriverWait(driver, 10)).until(new ExpectedCondition<Boolean>() {
			public Boolean apply(WebDriver search) {
				waitLoadBonitaIframe();
				return driver.findElement(By.id("search")).isDisplayed();
			}
		});

		// hacer click en la lista de procesos para que nos muestre todos los procesos
		WebElement search = driver.findElement(By.id("search"));
		search.sendKeys(strTarea);
		// hacemos la busqueda
		search.submit();
	}

	
	/**
	 * Asigno todas las tareas existentes al usuario que está logado.
	 */
	public void selecTask() {

		/**
		 * <td><span class="120928">
		 * <input id="ctl00_CM_ctl01_chkOptions_1" type="checkbox" name= "ctl00$CM$ctl01$chkOptions$1"/>
		 * <label for="ctl00_CM_ctl01_chkOptions_1">Seaside & Country Homes</label>
		 * </span></td>
		 */
		// label[text()='Seaside & Country Homes']/preceding-sibling::input[@type='checkbox']

		// El principio aquí es obtener la etiqueta con el texto que desea, luego
		// obtenga la casilla de verificación que está antes de la etiqueta, ya que esa
		// parece ser la forma en que se presenta su HTML.

		/*
		 * Si no está marcada, marcarla if (
		 * !driver.findElement(By.id("idOfTheElement")).isSelected() ) {
		 * driver.findElement(By.id("idOfTheElement")).click(); }
		 */

		/*
		 * 
		 * Para obtener todas las casillas de verificación, comenzarías un poco más
		 * arriba y luego trabajarías hacia abajo, es decir obtendrás la tabla, y luego
		 * obtendrás cualquier casilla dentro de un lapso:
		 * 
		 * //table/descendant::span/input[@type='checkbox']
		 * 
		 */

		// <td class="Cell--checkbox ng-scope" ng-if="canDoGroupAction()">
		// <input bo-selector="task" ng-click="onCheckBoxChange($event, $index)"
		// type="checkbox">
		// </td>

		// asegurarse de que se ha cargado la pagina y que hay tareas que seleccionar
		if (pendingTasks()) {
			(new WebDriverWait(driver, 10)).until(new ExpectedCondition<Boolean>() {
				public Boolean apply(WebDriver logout) {
					waitLoadBonitaIframe();
					// return
					// !driver.findElements(By.xpath("//table/descendant::span/input[@type='checkbox']")).isEmpty();
					return !driver.findElements(By.xpath("//table/descendant::input[@type='checkbox']")).isEmpty();
				}
			});

			// List<WebElement> selectElements =
			// driver.findElements(By.xpath("//table/td.Cell--checkbox.ng-scope/descendant::span/input[@type='checkbox']"));
			List<WebElement> selectElements = driver
					.findElements(By.xpath("//table/descendant::input[@type='checkbox']"));

			// seleccionar uno
			// List<WebElement> selectElements= driver.findElements(By.cssSelector("input[name='checkboxes[]']"));

			// td.Cell--checkbox.ng-scope
			// WebElement select = driver.findElement(By.tagName("select"));
			// List<WebElement> allOptions = select.findElements(By.tagName("option"));
			// for (WebElement option : allOptions) {
			// System.out.println(String.format("Value is: %s",
			// option.getAttribute("value")));
			// option.click();
			// }

			// con esto seleccionamos todos
			selectElements.get(0).click();

			// desasigno todas las tareas, por si habia alguna asignada
			// asegurarse de que se ha activado el botón
			(new WebDriverWait(driver, 10)).until(new ExpectedCondition<Boolean>() {
				public Boolean apply(WebDriver webDriver) {
					waitLoadBonitaIframe();
					return driver.findElement(By.id("btn-group-unassign")).isDisplayed();
				}
			});

			WebElement buttonUnassign = driver.findElement(By.id("btn-group-unassign"));

			if (buttonUnassign.isEnabled()) {
				buttonUnassign.click();
			} else {
				// no hay que desasignar, deselecciono los checks
				// iterar sobre todos y los desasigno si es necesario
				for (WebElement checkbox : selectElements) {
					// uncheck 'em all
					if (checkbox.isSelected()) {
						checkbox.click();
					}
				}

			}

			/*
			 * selectElements.get(0).click();
			 * 
			 * if( selectElements.get(2).isSelected()){ selectElements.get(2).click(); }
			 */
			// selecciono todas las tareas
			// for(WebElement checkbox : selectElements){
			// checkbox.click();
			// }

			if (pendingTasks()) {
				// Asignar las tareas al usuario activo
				selectElements = driver.findElements(By.xpath("//table/descendant::input[@type='checkbox']"));
				// con esto seleccionamos todos
				selectElements.get(0).click();

				// asegurarse de que se ha activado el botón
				(new WebDriverWait(driver, 10)).until(new ExpectedCondition<Boolean>() {
					public Boolean apply(WebDriver webDriver) {
						waitLoadBonitaIframe();
						return driver.findElement(By.id("btn-group-assigntome")).isDisplayed();
					}
				});

				WebElement buttonAssignToMe = driver.findElement(By.id("btn-group-assigntome"));

				if (buttonAssignToMe.isEnabled()) {
					buttonAssignToMe.click();
				}
			}

		}

	}

	private boolean pendingTasks() {

		// nos aseguramos de estar en el contenedor principal
		driver.switchTo().defaultContent();

		// asegurarse de que se ha activado el botón
		try {
			(new WebDriverWait(driver, 10)).until(new ExpectedCondition<Boolean>() {
				public Boolean apply(WebDriver webDriver) {
					waitLoadBonitaIframe();
					return driver.findElement(By.id("btn-group-assigntome")).isDisplayed();
				}
			});
		} catch (Exception e) {
			return false;
		}

		return true;
	}

	
	/**
	 * Abre la tarea en un popup modal
	 */
	public void openPopupDoTask() {

		/*
		  <button class="Toolbar-button Resize-full" ng-click="openDetailsPopup()"
		  title="Open in a popup"> 
		  <i class="glyphicon glyphicon-resize-full"></i>
		  <span class="sr-only" translate="">
		  <span class="ng-scope">Open in a popup</span></span> 
		  </button>
		 */

		// abrir tarea en un popup
		// Asignar las tareas al usuario activo

		String buttonPopupXPath = "//button[@title='Open in a popup']";
		//String buttonPopupCssSel = "button.Toolbar-button.Resize-full";
		// asegurarse de que se ha activado el botón
		(new WebDriverWait(driver, 10)).until(new ExpectedCondition<Boolean>() {
			public Boolean apply(WebDriver webDriver) {
				waitLoadBonitaIframe();
				return driver.findElement(By.xpath(buttonPopupXPath)).isDisplayed();
				//return !driver.findElements(By.cssSelector(buttonPopupCssSel)).isEmpty();
			}
		});

		driver.findElement(By.xpath(buttonPopupXPath)).click();
		//driver.findElements(By.cssSelector(buttonPopupCssSel)).get(0).click();

	}

	/**
	 * Con este metodo nos aseguramos que se ha cargado el contenedor principal del portal de bonita
	 * que esta contenido dentro de un iframe
	 */
	private void waitLoadBonitaIframe() {
		// nos aseguramos de estar en el contenedor principal
		driver.switchTo().defaultContent();

		// IMPORTANTE: CAMBIAMOS EL DRIVER A DENTRO DEL IFRAME DEL CUERPO, SINO NO
		// ENCONTRARÁ NADA.
		wait.until(ExpectedConditions.frameToBeAvailableAndSwitchToIt(By.id("bonitaframe")));
				
		
		/*
		// asegurarse de que se ha cargado la pagina
		(new WebDriverWait(driver, 10)).until(new ExpectedCondition<Boolean>() {
			public Boolean apply(WebDriver logout) {
				return driver.findElement(By.id("bonitaframe")).isDisplayed();
			}
		});

		// IMPORTANTE: CAMBIAMOS EL DRIVER A DENTRO DEL IFRAME DEL CUERPO, SINO NO
		// ENCONTRARÁ NADA.
		driver.switchTo().frame(driver.findElement(By.id("bonitaframe")));
		*/
	}
}
