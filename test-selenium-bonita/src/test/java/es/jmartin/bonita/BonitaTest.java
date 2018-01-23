/***
 * Una forma fácil de comenzar es este ejemplo, 
 * que busca el término "Cheese" en Google 
 * y luego muestra el título de la página de resultados en la consola.
 */

package es.jmartin.bonita;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import es.jmartin.selenium.support.SeleniumTest;

@RunWith(SpringRunner.class)
@SpringBootTest(properties = "server.port=9000", webEnvironment = SpringBootTest.WebEnvironment.DEFINED_PORT)
@SeleniumTest(driver = FirefoxDriver.class, baseUrl = "http://localhost:8080/bonita/login.jsp")
public class BonitaTest {

	@Autowired
	private WebDriver driver;

	public boolean isAlreadyLogIn = false;

	@Test
	public void prueba() {

		// login
		logIn("walter.bates", "bpm");

		
		//Filtrar tareas por proceso
		filtrarTareas("Selenium", "1.0", "Step1");
		
		//hacer una tarea
		//Revisar la lista y coger una tarea concreta. Por ejemplo la primera.
		
		
		// logout
		logOut();
	}

	
	public void logIn(String userID, String password) {
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

		driver.switchTo().defaultContent();
		
		// asegurarse de que se ha cargado la pagina
		(new WebDriverWait(driver, 10)).until(new ExpectedCondition<Boolean>() {
			public Boolean apply(WebDriver logout) {
				return driver.findElement(By.id("bonitaframe")).isDisplayed();
			}
		});
		
		//IMPORTANTE: NOS CAMBIAMOS EL DRIVER A DENTRO DEL IFRAME DEL CUERPO, SINO NO ENCONTRARÁ NADA.

		driver.switchTo().frame(driver.findElement(By.id("bonitaframe")));
		
//		By byXpath = By.xpath("//button[contains(text(),'All']");
//		By byXpath = By.xpath("//button[contains(@title='Process: All']");
//		WebElement myDynamicElement = (new WebDriverWait(driver, 10))
//		  .until(ExpectedConditions.presenceOfElementLocated(byXpath ));
//		myDynamicElement.click();
		
		
		
		// asegurarse de que se ha cargado la pagina
		(new WebDriverWait(driver, 10)).until(new ExpectedCondition<Boolean>() {
			public Boolean apply(WebDriver webDriver) {
				
//				driver.findElement(By.className("btn btn-primary dropdown-toggle")).click();
//				driver.findElement(By.xpath("//button[contains(@class='btn btn-primary dropdown-toggle')]")).click();
//				driver.findElement(By.xpath("//button[@class='btn btn-primary dropdown-toggle']")).click();
				
				
//				driver.findElements(By.cssSelector("button[title^=Process: All]"))
//				driver.findElement(By.cssSelector("#food span.dairy.aged"));
				return !driver.findElements(By.cssSelector("button.btn.btn-primary.dropdown-toggle")).isEmpty();
//				return driver.findElements(By.xpath("//button[contains(@class='btn btn-primary dropdown-toggle')]")).isDisplayed();
//				return !driver.findElements(By.className("btn btn-primary dropdown-toggle")).isEmpty();
//				return driver.findElements(By.xpath("//span/button[title()='Process All'][1]")).isDisplayed();
//				return driver.findElements(By.xpath("//button[contains(.,'<div class=\"ProcessList-label ng-binding\">')]")).isDisplayed();
						
						
			}
		});
		
		//tenemos que buscar el botón de desplegar los procesos instalados.
		driver.findElements(By.cssSelector("button.btn.btn-primary.dropdown-toggle")).get(0).click();
//		driver.findElement(By.xpath("//span/button[title()='Process All'][1]")).click();
		
		// asegurarse de que se ha cargado la pagina
//		(new WebDriverWait(driver, 10)).until(new ExpectedCondition<Boolean>() {
//			public Boolean apply(WebDriver logout) {
//				return !driver.findElements(By.className("processOptionLink")).isEmpty();
//			}
//		});
//		
//		// debemos hacer click en la lista de procesos para que nos muestre todos los procesos
//		// logout
//		WebElement allProcess = driver.findElement(By.partialLinkText("All"));
//		allProcess.click();
		
		// ahora ya podemos hacer click en el logout
		WebElement filtrarProceso = driver.findElement(By.linkText(strProceso.concat(" ").concat(strVersion)));
		filtrarProceso.click();
	}	

	
	public void filtrarTareas(String strProceso, String strVersion, String strTarea) {
		driver.switchTo().defaultContent();
		
		// asegurarse de que se ha cargado la pagina
		(new WebDriverWait(driver, 10)).until(new ExpectedCondition<Boolean>() {
			public Boolean apply(WebDriver logout) {
				return driver.findElement(By.id("bonitaframe")).isDisplayed();
			}
		});
		
		//IMPORTANTE: NOS CAMBIAMOS EL DRIVER A DENTRO DEL IFRAME DEL CUERPO, SINO NO ENCONTRARÁ NADA.

		driver.switchTo().frame(driver.findElement(By.id("bonitaframe")));
//        driver.findElement(By.xpath("//span[@class='label']")).click();
		
		
		//Filtro por proceso
		filtrarProceso(strProceso, strVersion);		
        
		// asegurarse de que se ha cargado la pagina
		(new WebDriverWait(driver, 10)).until(new ExpectedCondition<Boolean>() {
			public Boolean apply(WebDriver logout) {
				return driver.findElement(By.id("search")).isDisplayed();
			}
		});

		// debemos hacer click en la lista de procesos para que nos muestre todos los procesos
		// logout
		WebElement search = driver.findElement(By.id("search"));
		search.sendKeys(strTarea);
		//hacemos la busqueda
		search.submit();
	}		
}
