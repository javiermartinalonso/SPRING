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

		//hacer la primera tarea
		doFirstTask();  
	
		// logout
//		logOut();
	}

	/**
	 * realiza la primera tarea
	 */
	public void doFirstTask() {
		// hacer una tarea
		// Revisar la lista y coger una tarea concreta. Por ejemplo la primera.
		selecTask();

		//Abrir el popup para ejecutar la tarea en pantalla modal en primer plano
		openPopupDoTask();
		
		// sería interesante obtener el boton por la etiqueta u algo que lo identifique
		// univocamente, aunque modifiquemos el formulario metiendo nuevos inputs.
		By primerDigitoXpath = By.xpath("//label[text()='primerDigito']/following::input[@type='text']");
		WebElement input1 = wait.until(ExpectedConditions.elementToBeClickable(primerDigitoXpath));
//		WebElement input1 = wait.until(ExpectedConditions.elementToBeClickable(By.name("pbInput0")));
		//input1.click();           
		input1.sendKeys("69");
		
		//cerramos la popup modal
//		closeModalPopup();
		
	}

	/**
	 * Boton de cerrar la pantalla modal
	 */
	public void closeModalPopup() {
		// Boton de cerrar la pantalla modal
		By buttonCloseXPath = By.xpath("//button[@title='Close']");
		//By buttonCloseCssSel = By.cssSelector("button.pull-right.close.Modal-dismiss");
		//volver al contenedor principal, para cerrar la pantalla modal.
		waitLoadBonitaIframe();
		WebElement buttonClose = wait.until(ExpectedConditions.elementToBeClickable(buttonCloseXPath));
		buttonClose.click();
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
		// debemos hacer click en el nombre del usuario, para desplegar el boton de logout		
		By logoutXpath = By.xpath("//ul[@class=\"dropdownmenu userName\"]/li/a");
//		By logoutXpath = By.xpath("id(\"login\")/div[1]/div[@class=\"userData\"]/ul[@class=\"dropdownmenu userName\"]/li[@class=\"menuitem menuitem_userName downArrow userName _1 _last odd dropdownitem\"]/a[1]");
		
		WebElement buttonLogout = wait.until(ExpectedConditions.elementToBeClickable(logoutXpath));
		buttonLogout.click();
				
		// ahora ya podemos hacer click en el logout
		buttonLogout = driver.findElement(By.partialLinkText("Logout"));
		buttonLogout.click();
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
		WebElement searchButton = wait.until(ExpectedConditions.elementToBeClickable(By.cssSelector(desplegableProcesosCssSel)));
		// tenemos que hacer clik en el botón de desplegar los procesos instalados.	
		searchButton.click();	
		
		// hacer click en el proceso por el que queremos filtrar de la lista de elementos del desplegable 	
		String procesoLinkText = strProceso.concat(" ").concat(strVersion);
		WebElement filtrarProceso = wait.until(ExpectedConditions.elementToBeClickable(By.linkText(procesoLinkText)));	  
		filtrarProceso.click();
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
		
		//filtro por tarea
		By byIdSearch = By.id("search");
		WebElement buttonSearch = wait.until(ExpectedConditions.elementToBeClickable(byIdSearch));
		// hacer click en la lista de procesos para que nos muestre todos los procesos
		buttonSearch.click();
		//buscamos por la tarea
		buttonSearch.sendKeys(strTarea);
		// hacemos la busqueda
		buttonSearch.submit();
	}

	
	/**
	 * Asigno todas las tareas existentes al usuario que está logado.
	 */
	public void selecTask() {

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

		By taskListCheckboxXpath = By.xpath("//table/descendant::input[@type='checkbox']");
		By idBtnAssigntome = By.id("btn-group-assigntome");
		
		waitLoadBonitaIframe();
		
		// asegurarse de que se ha cargado la pagina y que hay tareas que seleccionar
		List<WebElement> selectElements = wait.until(ExpectedConditions.numberOfElementsToBeMoreThan(taskListCheckboxXpath, 1));
				
		//asigno la primera tarea sino está asignada
		if (!selectElements.get(1).isSelected()) {
			selectElements.get(1).click();
			
			// Asignar las tareas al usuario activo
			WebElement buttonAssignToMe = wait.until(ExpectedConditions.presenceOfElementLocated(idBtnAssigntome));
					
			//Si hay tareas que asignar
			if (buttonAssignToMe.isEnabled()) {
				buttonAssignToMe.click();
			}			
		}
		//si el usuario está asignado, selecciono la primera tarea para que cargue la tarea a la derecha de la pantalla
		else
		{
			By lineTaskByClass = By.className("Line ng-scope");
			List<WebElement> listTask = wait.until(ExpectedConditions.numberOfElementsToBeMoreThan(lineTaskByClass, 1));
			listTask.get(0).click();
		}
	}

	
	/**
	 * Abre la tarea en un popup modal y nos posicionamos en el iframe de la pantalla modal
	 */
	public void openPopupDoTask() {
		// asegurarse de que se ha activado el botón
		//By buttonPopupCssSel = By.cssSelector("button.Toolbar-button.Resize-full");
		By buttonPopupXPath = By.xpath("//button[@title='Open in a popup']");
		WebElement buttonPopup = wait.until(ExpectedConditions.elementToBeClickable(buttonPopupXPath));
		buttonPopup.click();
		
		// nos movemos a la ventana modal activa
		// asegurarse de que se ha cargado la pagina
		By idBonitaFrame = By.id("bonitaframe");

		//Esperamos a que se cargue el segundo iframe de bonita
		List <WebElement> listaIframesBonita = wait.until(ExpectedConditions.numberOfElementsToBeMoreThan(idBonitaFrame, 1));
		//nos posicionamos en el iframe de bonita de profundidad 2
		wait.until(ExpectedConditions.frameToBeAvailableAndSwitchToIt(listaIframesBonita.get(1)));
		
		/*		
		wait.until(new ExpectedCondition<Boolean>() {
			public Boolean apply(WebDriver iframes) {
				// return driver.findElements(By.id("bonitaframe")).get(1).isDisplayed();
				return driver.findElements(idBonitaFrame).size() == 2;
			}
		});
		// Nos movemos al iframe de profundidad 2
		driver.switchTo().frame(driver.findElements(idBonitaFrame).get(1));
		 */		
	}
	

	/**
	 * Con este metodo nos aseguramos que se ha cargado el contenedor principal del portal de bonita
	 * que esta contenido dentro de un iframe
	 */
	private void waitLoadBonitaIframe() {
		// nos aseguramos de estar en el contenedor principal
		driver.switchTo().defaultContent();

		// IMPORTANTE: CAMBIAMOS EL DRIVER A DENTRO DEL IFRAME DEL CUERPO, SINO NO ENCONTRARÁ NADA.
		wait.until(ExpectedConditions.frameToBeAvailableAndSwitchToIt(By.id("bonitaframe")));
	}
}
