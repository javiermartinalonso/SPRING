package es.jmartin.bonita;

import java.io.File;
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
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import es.jmartin.selenium.support.SeleniumTest;
//import io.github.bonigarcia.wdm.WebDriverManager;

@RunWith(SpringRunner.class)
@SpringBootTest(properties = "server.port=9000", webEnvironment = SpringBootTest.WebEnvironment.DEFINED_PORT)
//@SeleniumTest(driver = FirefoxDriver.class, baseUrl = "http://localhost:8080/bonita/login.jsp")
@SeleniumTest(driver = ChromeDriver.class, baseUrl = "http://localhost:8080/bonita/login.jsp")

//@TestPropertySource(properties = {"webdriver.gecko.driver = C:/selenium/geckodriver.exe"})
public class BonitaTest {

	@Autowired
	private WebDriver driver;

	public boolean isAlreadyLogIn = false;

	
//    @Before
//    public void setUp() throws Exception {
//        
//    }
    
 
    @BeforeClass
    public static void setupClass() {
    	
		String strFile = ClassLoader.getSystemClassLoader().getResource("seleniumDrivers/chromedriver-2.35.exe").getFile();
		
		System.setProperty("webdriver.chrome.driver",strFile);
//    	System.setProperty("webdriver.chrome.driver","C:\\selenium\\chromedriver.exe");
//    	System.setProperty("webdriver.gecko.driver","C:\\selenium\\geckodriver.exe");
    }

    @After
    public void teardown() {
//        if (driver != null) {
//            driver.quit();
//        }
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
		
		doTask();
		
		waitLoadBonitaIframe();
		
		//nos movemos a la ventana activa
		driver.switchTo().activeElement();
		
		// asegurarse de que se ha cargado la pagina
		(new WebDriverWait(driver, 10)).until(new ExpectedCondition<Boolean>() {
			public Boolean apply(WebDriver logout) {
//				return driver.findElements(By.id("bonitaframe")).get(1).isDisplayed();
				return !driver.findElements(By.id("bonitaframe")).isEmpty();
			}
		});
		
		// IMPORTANTE: CAMBIAMOS EL DRIVER A DENTRO DEL IFRAME DEL CUERPO, SINO NO
		// ENCONTRARÁ NADA.
		driver.switchTo().frame(driver.findElements(By.id("bonitaframe")).get(1));
		
		
		
		
		
		
		
		
		// asegurarse de que se ha cargado la pagina
				(new WebDriverWait(driver, 10)).until(new ExpectedCondition<Boolean>() {
					public Boolean apply(WebDriver logout) {


//sería interesante obtener el boton por la etiqueta u algo que lo identifique univocamente, aunque modifiquemos el formulario metiendo nuevos inputs.
//						return driver.findElement(By.xpath("//.modal-dialog/descendant::input[@name='pbInput1']")).isEnabled();
//						return driver.findElement(By.xpath("/html[1]/body[@class=\"ng-scope\"]/div[@class=\"container-fluid\"]/div[@class=\"row\"]/div[@class=\"ng-scope\"]/div[@class=\"col-xs-4 ng-scope\"]/div[@class=\"row\"]/div[@class=\"ng-scope\"]/div[@class=\"component col-xs-6 ng-scope\"]/pb-input[1]/div[@class=\"row\"]/div[@class=\"form-group\"]/div[@class=\"col-xs-12\"]/input[@class=\"form-control ng-untouched ng-valid ng-valid-minlength ng-valid-maxlength ng-valid-required ng-dirty ng-valid-parse\"]")).isEnabled();
						return driver.findElement(By.name("pbInput0")).isDisplayed();
					}
				});
				
				
								
						
		WebElement input1 = driver.findElement(By.name("pbInput0"));
//		WebElement input1 = driver.findElement(By.xpath("//.modal-dialog/descendant::input[@name='pbInput1']"));
//		WebElement input1 = driver.findElement(By.xpath("/html[1]/body[@class=\"ng-scope\"]/div[@class=\"container-fluid\"]/div[@class=\"row\"]/div[@class=\"ng-scope\"]/div[@class=\"col-xs-4 ng-scope\"]/div[@class=\"row\"]/div[@class=\"ng-scope\"]/div[@class=\"component col-xs-6 ng-scope\"]/pb-input[1]/div[@class=\"row\"]/div[@class=\"form-group\"]/div[@class=\"col-xs-12\"]/input[@class=\"form-control ng-untouched ng-valid ng-valid-minlength ng-valid-maxlength ng-valid-required ng-dirty ng-valid-parse\"]"));
		input1.sendKeys("69");
		

		
		/*Boton de cerrar la pantalla modal
		// asegurarse de que se ha cargado la pagina
		(new WebDriverWait(driver, 10)).until(new ExpectedCondition<Boolean>() {
			public Boolean apply(WebDriver logout) {
				waitLoadBonitaIframe();
//				return !driver.findElements(By.cssSelector("button.pull-right.close.Modal-dismiss")).isEmpty();
				return !driver.findElement(By.xpath("//span/button[title()='close'][1]")).isDisplayed();
			}
		});
		
		driver.findElements(By.cssSelector("button.pull-right.close.Modal-dismiss")).get(0).click();
		driver.findElement(By.xpath("//span/button[title()='close'][1]")).click();
*/
		
		
		
		
		// logout
		// logOut();
	}

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

	public void logOut() {

		// nos aseguramos de estar en el contenedor principal
		driver.switchTo().defaultContent();

		// logout
		// http://localhost:8080/bonita/logoutservice
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

	public void filtrarProceso(String strProceso, String strVersion) {

		if (strProceso.isEmpty()) {
			return;
		}

		waitLoadBonitaIframe();

		// By byXpath = By.xpath("//button[contains(text(),'All']");
		// By byXpath = By.xpath("//button[contains(@title='Process: All']");
		// WebElement myDynamicElement = (new WebDriverWait(driver, 10))
		// .until(ExpectedConditions.presenceOfElementLocated(byXpath ));
		// myDynamicElement.click();

		// asegurarse de que se ha cargado la pagina
		(new WebDriverWait(driver, 10)).until(new ExpectedCondition<Boolean>() {
			public Boolean apply(WebDriver webDriver) {
				waitLoadBonitaIframe();
				return !driver.findElements(By.cssSelector("button.btn.btn-primary.dropdown-toggle")).isEmpty();
				// return !driver.findElements(By.cssSelector("button[title^=Process:
				// All]")).isEmpty();
				// return driver.findElements(By.xpath("//button[contains(@class='btn
				// btn-primary dropdown-toggle')]")).isDisplayed();
				// return !driver.findElements(By.className("btn btn-primary
				// dropdown-toggle")).isEmpty();
				// return driver.findElements(By.xpath("//span/button[title()='Process
				// All'][1]")).isDisplayed();
				// return driver.findElements(By.xpath("//button[contains(.,'<div
				// class=\"ProcessList-label ng-binding\">')]")).isDisplayed();
			}
		});

		// tenemos que buscar el botón de desplegar los procesos instalados.
		// driver.findElements(By.cssSelector("button.btn.btn-primary.dropdown-toggle")).get(0).click();

		// driver.findElement(By.xpath("//span/button[title()='Process All'][1]")).click();

		// asegurarse de que se ha cargado la pagina
		// (new WebDriverWait(driver, 10)).until(new ExpectedCondition<Boolean>() {
		// public Boolean apply(WebDriver logout) {
		// return !driver.findElements(By.className("processOptionLink")).isEmpty();
		// }
		// });
		//
		// // debemos hacer click en la lista de procesos para que nos muestre todos los
		// procesos
		// // logout
		// WebElement allProcess = driver.findElement(By.partialLinkText("All"));
		// allProcess.click();

		waitLoadBonitaIframe();

		// asegurarse de que se ha cargado el elemento en la pagina
		(new WebDriverWait(driver, 10)).until(new ExpectedCondition<Boolean>() {
			public Boolean apply(WebDriver webDriver) {
				waitLoadBonitaIframe();
				// tenemos que hacer clcik en el botón de desplegar los procesos instalados.
				driver.findElements(By.cssSelector("button.btn.btn-primary.dropdown-toggle")).get(0).click();
				// buscamos en el desplegable el proceso por el que queremos filtrar
				return driver.findElement(By.linkText(strProceso.concat(" ").concat(strVersion))).isDisplayed();
			}
		});

		// hacer click en el desplegable el proceso por el que queremos filtrar
		WebElement filtrarProceso = driver.findElement(By.linkText(strProceso.concat(" ").concat(strVersion)));
		filtrarProceso.click();
	}

	public void filtrarTareas(String strProceso, String strVersion, String strTarea) {

		// Filtro por proceso
		filtrarProceso(strProceso, strVersion);

		// asegurarse de que se ha cargado la pagina
		(new WebDriverWait(driver, 10)).until(new ExpectedCondition<Boolean>() {
			public Boolean apply(WebDriver logout) {
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
	
	public void selecTask() {
		
		
		/**
		 *     <td>
        			<span class="120928"> 
        			<input id="ctl00_CM_ctl01_chkOptions_1" type="checkbox" name="ctl00$CM$ctl01$chkOptions$1"/> 
        			<label for="ctl00_CM_ctl01_chkOptions_1">Seaside & Country Homes</label> 
        			</span> 
    			</td> 
		 */
		//label[text()='Seaside & Country Homes']/preceding-sibling::input[@type='checkbox']
		
		//El principio aquí es obtener la etiqueta con el texto que desea, luego obtenga la casilla de verificación que está antes de la etiqueta, ya que esa parece ser la forma en que se presenta su HTML.
		
		/* Si no está marcada, marcarla
		if ( !driver.findElement(By.id("idOfTheElement")).isSelected() )
		{
		     driver.findElement(By.id("idOfTheElement")).click();
		}
		*/
		
		/*
		 
		 Para obtener todas las casillas de verificación, comenzarías un poco más arriba y luego trabajarías hacia abajo, es decir obtendrás la tabla, y luego obtendrás cualquier casilla dentro de un lapso:

		 //table/descendant::span/input[@type='checkbox']
		 
		 */
		
//		<td class="Cell--checkbox ng-scope" ng-if="canDoGroupAction()">
//        <input bo-selector="task" ng-click="onCheckBoxChange($event, $index)" type="checkbox">
//        </td>
      
        
		// asegurarse de que se ha cargado la pagina y que hay tareas que seleccionar
		if (pendingTasks())
		{
			// asegurarse de que se ha cargado la pagina y que hay tareas que seleccionar
			(new WebDriverWait(driver, 10)).until(new ExpectedCondition<Boolean>() {
				public Boolean apply(WebDriver logout) {
					waitLoadBonitaIframe();
//					return !driver.findElements(By.xpath("//table/descendant::span/input[@type='checkbox']")).isEmpty();
					return !driver.findElements(By.xpath("//table/descendant::input[@type='checkbox']")).isEmpty();
				}
			});
			
//			List<WebElement> selectElements = driver.findElements(By.xpath("//table/td.Cell--checkbox.ng-scope/descendant::span/input[@type='checkbox']"));
			List<WebElement> selectElements = driver.findElements(By.xpath("//table/descendant::input[@type='checkbox']"));

			//seleccionar uno
//			List<WebElement> selectElements= driver.findElements(By.cssSelector("input[name='checkboxes[]']"));
			
			
//			td.Cell--checkbox.ng-scope
//			WebElement select = driver.findElement(By.tagName("select"));
//			List<WebElement> allOptions = select.findElements(By.tagName("option"));
//			for (WebElement option : allOptions) {
//			    System.out.println(String.format("Value is: %s", option.getAttribute("value")));
//			    option.click();
//			}
			
			
			
			
			//con esto seleccionamos todos
			selectElements.get(0).click();

			//desasigno todas las tareas, por si habia alguna asignada
			// asegurarse de que se ha activado el botón
			(new WebDriverWait(driver, 10)).until(new ExpectedCondition<Boolean>() {
				public Boolean apply(WebDriver webDriver) {
					waitLoadBonitaIframe();
					return driver.findElement(By.id("btn-group-unassign")).isDisplayed();
				}
			});
			
			WebElement buttonUnassign = driver.findElement(By.id("btn-group-unassign"));
			
			if (buttonUnassign.isEnabled())
			{
				buttonUnassign.click();
			}
			else
			{
				//no hay que desasignar, deselecciono los checks
				//iterar sobre todos y los desasigno si es necesario
				for(WebElement checkbox : selectElements){				
				    // uncheck 'em all
				    if(checkbox.isSelected()){
				      checkbox.click();
				    }
				}
				
			}
			
			
	/*
					selectElements.get(0).click();

					if( selectElements.get(2).isSelected()){
					    selectElements.get(2).click();
					}
		*/			
					//selecciono todas las tareas
//					for(WebElement checkbox : selectElements){
//						checkbox.click();
//					}
			
			if (pendingTasks())
			{
				//	Asignar las tareas al usuario activo
				selectElements = driver.findElements(By.xpath("//table/descendant::input[@type='checkbox']"));
				//con esto seleccionamos todos
				selectElements.get(0).click();
				
						// asegurarse de que se ha activado el botón
						(new WebDriverWait(driver, 10)).until(new ExpectedCondition<Boolean>() {
							public Boolean apply(WebDriver webDriver) {
								waitLoadBonitaIframe();
								return driver.findElement(By.id("btn-group-assigntome")).isDisplayed();
							}
						});
						
						
						WebElement buttonAssignToMe = driver.findElement(By.id("btn-group-assigntome"));	
						
						if (buttonAssignToMe.isEnabled())
						{
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
	
	
	public void doTask() {
		
		
		/*
		<button class="Toolbar-button Resize-full" ng-click="openDetailsPopup()" title="Open in a popup">
        <i class="glyphicon glyphicon-resize-full"></i>
        <span class="sr-only" translate=""><span class="ng-scope">Open in a popup</span></span>
	    </button>
	    */
		
		//abrir tarea en un popup
//		Asignar las tareas al usuario activo
		
		// asegurarse de que se ha activado el botón
		(new WebDriverWait(driver, 10)).until(new ExpectedCondition<Boolean>() {
			public Boolean apply(WebDriver webDriver) {
				waitLoadBonitaIframe();
//				return driver.findElement(By.xpath("//span/button[title()='Open in a popup']")).isDisplayed();
				return !driver.findElements(By.cssSelector("button.Toolbar-button.Resize-full")).isEmpty();
				// return !driver.findElements(By.cssSelector("button[title^='Open in a popup']")).isEmpty();
			}
		});
		
		
//		driver.findElement(By.xpath("//span/button[title()='Open in a popup']")).click();		
		driver.findElements(By.cssSelector("button.Toolbar-button.Resize-full")).get(0).click();
    
	}
	
	
	

	private void waitLoadBonitaIframe() {
		// nos aseguramos de estar en el contenedor principal
		driver.switchTo().defaultContent();

		// asegurarse de que se ha cargado la pagina
		(new WebDriverWait(driver, 10)).until(new ExpectedCondition<Boolean>() {
			public Boolean apply(WebDriver logout) {
				return driver.findElement(By.id("bonitaframe")).isDisplayed();
			}
		});

		// IMPORTANTE: CAMBIAMOS EL DRIVER A DENTRO DEL IFRAME DEL CUERPO, SINO NO
		// ENCONTRARÁ NADA.
		driver.switchTo().frame(driver.findElement(By.id("bonitaframe")));
	}
}
